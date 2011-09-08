(ns news-crawler.downloader
  (:require [clojure.contrib.http.agent :as h])
  (:require [clojure.contrib.duck-streams :as d]))

(defn wait
  "Halts execution the amount of milliseconds given
   Usefull for avoiding anti-crawler scripts"
  [milliseconds]
  (Thread/sleep milliseconds) true)

(defn rand-from-to
  "Random number from - to (both inclusive)"
  [from to]
  (+ (rand-int (- to (dec from))) from))

(defn download
  "Download the data in the given URL using HTTP Agents
   Args:
     file-name - The file name to save the data in
     url - The URL to fetch
     milliseconds - Pause after download"
  [file-name url milliseconds]
  (h/http-agent url
                :handler (fn [agnt]
                           (let [fname file-name]
                             (do (wait milliseconds)
                                 (with-open [w (d/writer fname)]
                                   (d/copy (h/stream agnt) w)))))))
 
(defn download-all
  "Download data in parallel
   Args:
     url-data - vector of file names and URLs
     n-streams - number of parallel downloads
     path - folder to store files, requires trailing slash
   Adjust (rand-from-to x y) to circumvent anti-crawler scrips"
  [url-data n-streams path]
  (let [p-url-data (partition-all n-streams url-data)
        make-path (d/make-parents (d/file-str (str path "dummy")))]
    (doseq [url p-url-data]
      (let [agnts (pmap #(download (str path (first %)) (second %)
                                   (rand-from-to 750 1500)) url)]
        (apply await agnts)))))

