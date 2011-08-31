(ns news-crawler.parser
  (:use [net.cgrand.enlive-html])
  (:use [clojure.contrib.duck-streams :only (reader)]))


(defn file->map [file]
  (with-open [r (reader file)]
    (html-resource r)))

(defn all-links [html-map]
  (map #(:href (:attrs %)) (select html-map [:a])))

; (spit "fetched/bt-a" (seq (all-links k)))


