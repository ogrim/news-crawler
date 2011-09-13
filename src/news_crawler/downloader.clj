(ns news-crawler.downloader
  (:require [clojure.contrib.duck-streams :as d])
  (:require [work.core :as w]))

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
  "Returns the web page as a string
   Args:
     url - The URL to fetch
     milliseconds - Pause before download"
  [url milliseconds]
  (do (wait milliseconds)
      (d/slurp* url)))

(defmacro dl-function [url milliseconds]
  `#(download ~url ~milliseconds))

(defn download-all
  "Download data in parallel and returns a list of strings
   Args:
     url-data - vector of URLs
     n-streams - number of parallel downloads
   Adjust (rand-from-to x y) to circumvent anti-crawler scrips"
  [urls streams]
  (w/work (map #(dl-function % (rand-from-to 750 1500)) urls) streams))

