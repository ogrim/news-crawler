(ns news-crawler.parser
  (:require [net.cgrand.enlive-html :as e]
            [clojure.contrib.duck-streams :as d]))

(def *url-data* [["bt" "http://bt.no"]
                 ["ba" "http://ba.no"]
                 ])

(defn fetch-url [filename url]
  (let [in (str (seq (e/html-resource (java.net.URL. url))))]
   (with-open [w (d/writer filename)]
     (d/copy in w))))

(defn fetch-all [url-data]
  (doseq [url url-data]
    (apply await (fetch-url (str "fetched/" (first url)) (second url)))))

(defn fetch-parallel [n-streams url-data]
  (let [p-url (partition-all (/ (count url-data) n-streams) url-data)]
    (map #(fetch-all %) p-url)))

(defn get-hyperlinks [file]
  (with-open [r (d/reader file)]
    r))




;; (defn hn-headlines [page]
;;   (map html/text (html/select page [:td.title :a])))

;; (defn hn-points [page]
;;   (map html/text (html/select page [:td.subtext html/first-child])))

;; (defn print-headlines-and-points [page]
;;   (doseq [line (map #(str %1 " (" %2 ")") (hn-headlines page) (hn-points page))]
;;     (println line)))


