(ns news-crawler.core
  (:require [news-crawler.downloader :as d])
  (:use [news-crawler.parser])
  (:use [clj-time.core :only (now year month day)]))


(defilter bt-filter "nyheter" ".html\\b" ".ece\\b")
(defilter ba-filter ".ece\\b" "nyheter" "puls")

(def *url-data* [["bt" "http://bt.no" bt-filter]
                 ["ba" "http://ba.no" ba-filter]
                 ])

(defn current-date
  "Current date as yyyy-m-d"
  []
  (let [date (now)]
    (str (year date) "-" (month date) "-" (day date))))

(defn urls->vector
  "Convert a coll of URLs to downloadable format
   Example:
   [\"url1\" \"url2\"] -> [[\"bt-1\" \"url1\"] [\"bt-2\" \"url2\"]]"
  [basename urls]
  (let [i (ref 0)]
    (map #(vector (str basename "_" (dosync (alter i inc))) %) urls)))

(defn append-date [urls]
  (map #(vector (str (first %) "_" (current-date)) (second %) (second (rest %))) urls))

(defn download-daily []
  (d/download-all (append-date *url-data*) 2 "fetched/"))

(defn parse-daily []
  (let [filenames (map #(first %) (append-date *url-data*))]
    (file->map (str "fetched/" (first filenames)))))


; gudefunksjon, refaktorer
(defn daily []
  (let [urls (append-date *url-data*)]
    (do (d/download-all urls "fetched/")
        (let [links (map #(all-links (file->map (str "fetched/" (first %)))) urls)
              validated (map #(filter validate-link %) links)
              filtered (map (fn [filter-func valid-urls]
                              (filter (second (rest filter-func)) valid-urls))
                            urls validated)]
          ;skriv filtered til fil, eller send til downloader
          ))))



; (d/download-all *url-data* 2 "fetched/")
; (d/download-all bt-urls-vectors 2 "fetched/")




