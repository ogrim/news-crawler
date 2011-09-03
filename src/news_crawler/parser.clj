(ns news-crawler.parser
  (:use [net.cgrand.enlive-html])
  (:use [clojure.contrib.duck-streams :only (reader)]))


(defn file->map [file]
  (with-open [r (reader file)]
    (html-resource r)))

(defn all-links [html-map]
  (distinct (map #(:href (:attrs %)) (select html-map [:a]))))

(defn validate-link [link]
  (cond (< (count link) 11) false
        (= (subs link 0 4) "http") true
        :else false))

(defn link-filter
  ([link must-contain] (if (re-seq (re-pattern must-contain) link) true false))
  ([link must-contain & or-clauses]
  (let [expanded-clauses (filter #(re-seq (re-pattern %) link) or-clauses)]
    (if (and (re-seq (re-pattern must-contain) link)
             (< 0 (count expanded-clauses)))
      true false))))


(comment
(def links (all-links (file->map "fetched/bt")))
(def validated-links (filter #(validate-link %) links))
(def bt-filter #(link-filter % "nyheter" ".html\\b" ".ece\\b"))
(def filtered-bt (filter bt-filter validated-links))
(spit "fetched/bt-a" (seq (filtered-bt)))
)


