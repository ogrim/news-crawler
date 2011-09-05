(ns news-crawler.core
  (:require [news-crawler.downloader :as d])
  (:use [news-crawler.parser])
  (:use [clj-time.core :only (now year month day)]))

(def *url-data* [["bt" "http://bt.no"]
                 ["ba" "http://ba.no"]
                 ])

(defn current-date []
  (let [date (now)]
    (str (year date) "-" (month date) "-" (day date))))

(defn urls->vector
  "Convert a coll of URLs to downloadable format
   Example:
   [\"url1\" \"url2\"] -> [[\"bt-1\" \"url1\"] [\"bt-2\" \"url2\"]]"
  [basename urls]
  (let [i (ref 0)]
    (map #(vector (str basename "_" (current-date) "_" (dosync (alter i inc))) %) urls)))

(defilter bt-filter "nyheter" ".html\\b" ".ece\\b")
(defilter ba-filter ".ece\\b" "nyheter" "puls")


; (d/download-all *url-data* 2 "fetched/")
; (d/download-all bt-urls-vectors 2 "fetched/")




