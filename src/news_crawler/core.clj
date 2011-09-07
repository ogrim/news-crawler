(ns news-crawler.core
  (:require [news-crawler.downloader :as d])
  (:use [news-crawler.parser])
  (:use [clj-time.core :only (now year month day)]))

(defilter bt-filter "nyheter" ".html\\b" ".ece\\b")
(defparser bt-parser [:article :> :div :h1] [:article :p])

(defilter ba-filter "(ba.no)+(.)*(.ece\\b)" "nyheter")
(defparser ba-parser [:div.apiArticleTop :h1] [:div.apiArticleText :p])

(def *out-dir* "fetched/")

;Defines the sites to scrape
;[name url article-selector article-filter article-parser]
(def *url-data*  
  [["bt" "http://bt.no" [:h2 :a] bt-filter bt-parser]
   ["ba" "http://ba.no" [:h3 :a] ba-filter ba-parser]])

(defn current-date
  "Current date as yyyy-m-d"
  []
  (let [date (now)]
    (str (year date) "-" (month date) "-" (day date))))

(defn urls->vector
  "Convert a coll of URLs to downloadable format with basename and current date
   Example:
   [\"url1\" \"url2\"] -> [[\"bt_2011-9-6_1\" \"url1\"] [\"bt_2011-9-6_2\" \"url2\"]]"
  [basename urls]
  (let [i (ref 0)]
    (map #(vector (str basename "_" (current-date) "_" (dosync (alter i inc))) %) urls)))

(defn append-date [urls]
  (map (fn [[name & more]] (concat (list (str name "_" (current-date))) more)) urls))

(defn parse-daily [urls]
  (let [links (map (fn [[filename _ selector]]
                     (all-links (file->map (str *out-dir* filename)) selector))
                   urls)
        validated (map #(filter validate-link %) links)]
    (map (fn [[_ _ _ filter-func] valid-urls] (filter filter-func valid-urls))
         urls validated)))

(defn parse-articles [filenames parser]
  (map #(conj (parser (file->map (str *out-dir* (current-date) "/" (first %))))
              {:url (second %)}
              {:date (current-date)}) filenames))

(defn daily []
  (let [urls (append-date *url-data*)]
    (do (d/download-all urls 2 *out-dir*)
        (let [parsed (parse-daily urls)
              downloadable (map #(urls->vector (first %1) %2) *url-data* parsed)]
          (do (d/download-all
               (reduce concat downloadable) 4 (str *out-dir* (current-date) "/"))
              (map (fn [filenames [_ _ _ _ parser]]
                     (parse-articles filenames parser)) downloadable *url-data*))))))


(comment
  (let [i (ref 0)
        j (count bta)]
    (loop []
      (if (< @i j)
        (do (dosync (println (alter i inc)))
            ()
            (recur))))))


