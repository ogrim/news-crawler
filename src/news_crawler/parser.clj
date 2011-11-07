(ns news-crawler.parser
  (:use [net.cgrand.enlive-html])
  (:use [clojure.contrib.duck-streams :only (reader)])
  (:require [clojure.string :as str]))

(defn file->map
  "Reads a html file and returns a map"
  [file]
  (with-open [r (reader file)]
    (html-resource r)))

(defn str->map
  "Reads a html as string and returns a map"
  [string]
  (-> string java.io.StringReader. html-resource))

(defn all-links
  "Returns all links from the html-map by a given selector"
  [html-map selector]
  (distinct (map #(:href (:attrs %)) (select html-map selector))))

(defn validate-link
  "Simplistic link validation such as minimum length"
  [link]
  (cond (< (count link) 11) false
        (= (subs link 0 4) "http") true
        :else false))

(defn extract-subtag [tag]
  (let [submap (map map? (:content tag))]
    (reduce str (remove empty? (map (fn [content ismap?]
                                      (if ismap? (first (:content content)) content))
                                    (:content tag) submap)))))

(defn submaps [content]
  (map #(some map? (:content %)) content))

(defn clean-str [s1 s2]
  (str s1 (str/replace s2 "\n" "") " "))

(defn trim-clean-left
  "Trims the string and removes unvanted characters from the start
  (trim-clean [\\- \\!] \" ! --  Hello!\") -> \"Hello!\""
  [unvanted s]
  (loop [trim (.trim s)]
    (if (some #{(first trim)} unvanted)
      (recur (.trim (apply str (drop 1 trim))))
      trim)))

(defn trim-clean-right [unvanted s]
  (loop [trim (.trim s)]
    (if (some #{(last trim)} unvanted)
      (recur (.trim (.substring trim 0 (dec (count trim)))))
      trim)))


(defn link-filter
  ([link must-contain]
     (if (re-seq (re-pattern must-contain) link) true false))
  ([link must-contain & or-clauses]
     (let [expanded-clauses (filter #(re-seq (re-pattern %) link) or-clauses)]
       (if (and (re-seq (re-pattern must-contain) link)
                (< 0 (count expanded-clauses)))
         true false))))

(defmacro defilter
  "Defines filters functions to find articles on the main pages
   Logic:
     must-contain AND (or-arg1 OR or-arg2 OR ..)"
  ([name must-contain]
     `(def ~name (fn [link#] (link-filter link# ~must-contain))))
  ([name must-contain & or-args]
     `(def ~name (fn [link#] (link-filter link# ~must-contain ~@or-args)))))

(defn parse-article
  "Extracts title and body text from a html-map by using selectors"
  [html-map title body & titlesplit]
  (let [content (select html-map body)
        submap (submaps content)
        unvanted [\- \– \ ]
        titleprocessed (->> (select html-map title) (first)
                            (:content) (first) (trim-clean-left unvanted))]
    {:title (if (seq (first titlesplit))
              (->> (first titlesplit)
                   (re-pattern)
                   (str/split titleprocessed)
                   (drop-last)
                   (apply str)
                   (trim-clean-right unvanted))
            titleprocessed)
     :body (->> (map (fn [tag ismap?]
                       (if ismap? (extract-subtag tag)
                           (first (:content tag)))) content submap)
                (remove empty?)
                (reduce clean-str "")
                (trim-clean-left unvanted))}))

(defmacro defparser
  "Defines parser function to find title and body text from an html-map"
  ([name title body]
     `(def ~name (fn [html-map#] (parse-article html-map# ~title ~body))))
  ([name title body titlesplit]
     `(def ~name (fn [html-map#] (parse-article html-map# ~title ~body ~titlesplit)))))

