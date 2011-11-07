(ns news-crawler.db
  (:require [clojure.java.jdbc :as sql]))

(defn create-db [db]
  (try (sql/with-connection db 
         (sql/create-table "artikler.data"
                           [:id "serial"]
                           [:date :text]
                           [:url :text]
                           [:title :text]
                           [:body :text]))
       (catch Exception e (println e))))

(defn insert [db article-map]
  (sql/with-connection db
    (sql/insert-records :news article-map)))

(defn insert-articles
  "Inserts all articles into database"
  [db articles]
  (let [inserted (ref [])]
    (sql/with-connection db
      (doseq [article-map articles]
        (let [insert (sql/insert-records "artikler.data" article-map)]
          (dosync (alter inserted conj insert )))))
    @inserted))

(defn migrate [db-from db-to]
  (let [data (sql/with-connection db-from
               (sql/with-query-results rs ["select * from news"]
                 (doall rs)))]
    (sql/with-connection db-to
      (doseq [article data]
        (sql/insert-records "artikler.data" article)))))

(defn url-unique? [db url]
  (sql/with-connection db
    (sql/with-query-results rs
      ["select url from artikler.data where url =?" url]
      (doall rs))))

(defn unique-articles [db article-maps]
  (filter #(empty? (url-unique? db (:url %))) article-maps))

