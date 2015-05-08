(defproject dwolla-sdk-clojure "0.1.2"
  :description "Dwolla API wrapper in Clojure"
  :url "http://github.	com/andrewhavck/dwolla-sdk-clojure"
  :license {:name "The MIT License (MIT)"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/data.json "0.2.6"]
                 [clj-http "1.1.2"]
                 [org.clojure/core.match "0.2.2"]]
  :profiles {:dev { :dependencies [[org.clojure/tools.namespace "0.2.10"]
                                  [org.clojure/java.classpath "0.2.2"]
                                  [midje "1.6.3"]
                                  [bultitude "0.2.6"]]
                     :plugins [[lein-midje "3.1.1"]]}})
