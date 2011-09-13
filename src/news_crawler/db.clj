(ns news-crawler.db
  (:require [clojure.java.jdbc :as sql]))

(defn create-db [db]
  (try (sql/with-connection db 
         (sql/create-table :news
                       [:date :text]
                       [:url :text]
                       [:title :text]
                       [:body :text]))
       (catch Exception e (println e))))

(defn insert-articles [db articles]
  (map #(insert db %) articles))

(defn insert [db article-map]
  (sql/with-connection db
    (sql/insert-records :news article-map)))

