(ns news-crawler.downloader
  (:require [clojure.contrib.http.agent :as h])
  (:require [clojure.contrib.duck-streams :as d]))
 
(defn download
  "Download the data in the given URL using HTTP Agents
   Args:
     file-name - The file name to save the data in
     url - The URL to fetch
  "
  [file-name url]
  (h/http-agent url
                :handler (fn [agnt]
                           (let [fname file-name]
                             (with-open [w (d/writer fname)]
                               (d/copy (h/stream agnt) w))))))
 
(defn download-all
  "Download data in parallel
   Args:
     url-data - vector of file names and URLs
     n-streams - number of parallel downloads
     path - folder to store files
  "
  [url-data n-streams path]
  (let [p-url-data (partition-all (/ (count url-data) n-streams) url-data)]
    (doseq [url p-url-data]
      (let [agnts (map #(download (str path (first %)) (second %)) url)]
        (apply await agnts)))))

