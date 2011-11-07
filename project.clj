(defproject news-crawler "2.2.0"
  :description "Scrapes online newspapers and puts content of articles into a database"
  :dependencies [[org.clojure/clojure "1.2.1"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [enlive "1.0.0"]
                 [clj-time "0.3.0"]
                 [org.clojure/java.jdbc "0.0.6"]
                 [postgresql "9.0-801.jdbc4"]
                 [org.xerial/sqlite-jdbc "3.6.13"]
                 [work "0.0.1"]]
  :dev-dependencies [[org.clojars.weavejester/autodoc "0.9.0"]])
