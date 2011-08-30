(ns news-crawler.downloader
  (:require [clojure.contrib.http.agent :as h])
  (:require [clojure.contrib.duck-streams :as d]))

(def url-data [["bt" "http://bt.no"]
               ["ba" "http://ba.no"]
               ])
 
(defn download
  "Download the data in the given URL using HTTP Agents
   Args:
     file-name - The file name to save the data in
     url - The URL to fetch
  "
  [file-name url]
  (h/http-agent url
                :handler (fn [agnt]
                           (let [fname file-name]  ; File name in a closure
                             (with-open [w (d/writer fname)]
                               (d/copy (h/stream agnt) w))))))
 
;; (defn download-all
;;   "Download all the URLs
;;    Args:
;;      url-data - A vector of vectors containing the file name and the url
;;   "
;;   [url-data]
;;   (doseq [[file-name url] url-data]
;;     (download file-name url)))
 
;(download-all url-data)

(def partitioned-data (partition-all 5 url-data)) ;; 15 being the max parallel downloads
 
(defn download-all
  "Download all the files, step by step
   Args:
     p-url-data - Partitioned url data
  "
  [p-url-data]
  (doseq [url-data p-url-data]
    (let [agnts (map #(download (first %) (second %)) url-data)]
      (apply await agnts)))) ; Wait till the agents finish
 
;(download-all partitioned-data)