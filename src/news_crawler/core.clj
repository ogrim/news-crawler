(ns news-crawler.core
  (:require [news-crawler.downloader :as d])
  (:use [news-crawler.parser])
  (:use [clj-time.core :only (now year month day)]))

(defilter bt-filter "nyheter" ".html\\b" ".ece\\b")
(defilter ba-filter "(ba.no)+(.)*(.ece\\b)" "nyheter" "puls")

(def *out-dir* "fetched/")
(def *url-data* [["bt" "http://bt.no" bt-filter]
                 ["ba" "http://ba.no" ba-filter]])

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
  (map #(vector (str (first %) "_" (current-date)) (second %) (second (rest %))) urls))

(defn download-daily [urls]
  (d/download-all urls 2 *out-dir*))

(defn parse-daily [urls]
  (let [links (map #(all-links (file->map (str *out-dir* (first %)))) urls)
        validated (map #(filter validate-link %) links)]
    (map (fn [filter-func valid-urls] (filter (second (rest filter-func)) valid-urls))
         urls validated)))

(defn daily []
  (let [urls (append-date *url-data*)]
    (do (download-daily urls)
        (let [parsed (parse-daily urls)
              downloadable (map #(urls->vector (first %1) %2) *url-data* parsed)]
          (d/download-all
           (reduce concat downloadable) 4 (str *out-dir* (current-date) "/"))))))

