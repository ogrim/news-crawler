(ns news-crawler.core
  (:require [news-crawler.downloader :as d])
  (:use [news-crawler.parser]))

(def *url-data* [["bt" "http://bt.no"]
                 ["ba" "http://ba.no"]
                 ])

(defn urls->vector
  "Convert a coll of URLs to downloadable format
   Example:
   [\"url1\" \"url2\"] -> [[\"bt-1\" \"url1\"] [\"bt-2\" \"url2\"]]"
  [basename urls]
  (let [i (ref 0)]
    (map #(vector (str basename "-" (dosync (alter i inc))) %) urls)))

(defilter bt-filter "nyheter" ".html\\b" ".ece\\b")
(defilter ba-filter ".ece\\b" "nyheter" "puls")


; (d/download-all *url-data* 2 "fetched/")
; (d/download-all bt-urls-vectors 2 "fetched/")





