(ns news-crawler.core
  (:require [news-crawler.downloader :as d])
  (:use [news-crawler.parser])
  (:use [clj-time.core :only (now year month day)])
  (:use [news-crawler.db]))

(defilter bt-filter "nyheter" ".html\\b" ".ece\\b")
(defparser bt-parser [:article :> :div :h1] [:article :div.bodyText :> :p])

(defilter ba-filter "(ba.no)+(.)*(.ece\\b)" "nyheter")
(defparser ba-parser [:div.apiArticleTop :h1] [:div.apiArticleText :p])

(def
  ^{:doc "Output directory, where files get stored"}
  *out-dir* "fetched/")

(def
  ^{:doc "Defines the sites to scrape, and what filter and parser functions to use.
  The selector matches articles on the front page, while the filter will reduce
  the selection, the parser will scrape the article content.
     [[name url article-selector article-filter article-parser][..]]"}
  *url-data*  
  [["bt" "http://bt.no" [:h2 :a] bt-filter bt-parser]
   ["ba" "http://ba.no" [:h3 :a] ba-filter ba-parser]])

(def
  ^{:doc "Defines database settings, which are passed to news-crawler.db"}
  *db*
  {:classname   "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname     (str *out-dir* "database.db")})

(defn current-date
  "Current date as yyyy-m-d"
  []
  (let [date (now)]
    (str (year date) "-" (month date) "-" (day date))))

(defn url->links
  "Extracts the article links from url using selector and filter-function"
  [url selector filter-func]
  (let [content (d/download url 0)
        links (filter validate-link (all-links (str->map content) selector))]
    (filter filter-func links)))

(defn parse-articles
  "Takes a list of downloaded articles, urls and the parser.
   Extracts the content of the articles to maps"
  [html-list parser urls]
  (map #(conj (parser (str->map %1))
             {:url %2}
             {:date (current-date)}) html-list urls))

(defn scrape
  "Runs the scraping with configurations defined by *out-dir*, *url-data* and *db*"
  []
  (do (create-db *db*)
      (let [links (map (fn [[_ url sel f]] (url->links url sel f)) *url-data*)
            articles (map #(d/download-all % 4) links)
            parsed (map #(parse-articles %1 (nth %2 4) %3) articles *url-data* links)
            unique (map #(unique-articles *db* %) parsed)
            inserted (map #(insert-articles *db* %) unique)
            counted (reduce #(+ %1 (count %2)) 0 inserted)]
        (str counted (if (= counted 1) " new article" " new articles")))))

