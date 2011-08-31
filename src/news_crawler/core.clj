(ns news-crawler.core
  (:require [news-crawler.downloader :as d]))

(def *url-data* [["bt" "http://bt.no"]
                 ["ba" "http://ba.no"]
                 ])

; (d/download-all *url-data* 2 "fetched/")








