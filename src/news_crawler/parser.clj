(ns news-crawler.parser
  (:use [net.cgrand.enlive-html])
  (:use [clojure.contrib.duck-streams :only (reader)]))


(defn file->map [file]
  (with-open [r (reader file)]
    (html-resource r)))

(defn all-links [html-map]
  (map #(:href (:attrs %)) (select html-map [:a])))

(defn link-filter [link]
  (cond (< (count link) 11) false
        (= (subs link 0 4) "http") true
        :else false))

