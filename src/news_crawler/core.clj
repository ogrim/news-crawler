(ns news-crawler.core
  (:require [news-crawler.downloader :as d]))

(def *url-data* [["bt" "http://bt.no"]
                 ["ba" "http://ba.no"]
                 ])

(defn urls->vector [urls]
  (let [i (ref 0)]
    (map #(vector (dosync (alter i inc)) %) urls)))

; (d/download-all *url-data* 2 "fetched/")
; (d/download-all bt-urls-vectors 2 "fetched/")





