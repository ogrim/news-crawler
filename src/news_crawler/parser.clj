(ns news-crawler.parser
  (:use [net.cgrand.enlive-html])
  (:use [clojure.contrib.duck-streams :only (reader)]))

(defn file->map [file]
  (with-open [r (reader file)]
    (html-resource r)))

(defn all-links [html-map selector]
  (distinct (map #(:href (:attrs %)) (select html-map selector))))

(defn validate-link [link]
  (cond (< (count link) 11) false
        (= (subs link 0 4) "http") true
        :else false))

(defn link-filter
  ([link must-contain]
     (if (re-seq (re-pattern must-contain) link) true false))
  ([link must-contain & or-clauses]
     (let [expanded-clauses (filter #(re-seq (re-pattern %) link) or-clauses)]
       (if (and (re-seq (re-pattern must-contain) link)
                (< 0 (count expanded-clauses)))
         true false))))

(defmacro defilter
  ([name must-contain]
     `(def ~name (fn [link#] (link-filter link# ~must-contain))))
  ([name must-contain & or-args]
     `(def ~name (fn [link#] (link-filter link# ~must-contain ~@or-args)))))


(comment
(def links (all-links (file->map "fetched/bt-old")))
(def validated-links (filter validate-link links))
(defilter bt-filter "nyheter" ".html\\b" ".ece\\b")
(def filtered-bt (filter bt-filter validated-links))
(spit "fetched/bt-a" (seq (filtered-bt)))

(def bap (file->map "fetched/2011-9-6/ba_2011-9-6_10"))
(def baf (file->map "fetched/ba_2011-9-6"))

(def btf (file->map "fetched/bt_2011-9-6"))
(def btp (file->map "fetched/2011-9-6/bt_2011-9-6_10"))
(drop 1 (select btp [:article :> :div]))

;finn alle artikkellenker på bt:
(map #(:href (:attrs %)) (select btf [:h2 :a]))

(map #(:href (:attrs %)) (select baf [:h3 :a]))


)

