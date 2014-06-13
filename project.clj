(defproject dwolla-sdk-clojure "0.1.1"
  :description "Dwolla API wrapper in Clojure"
  :url "http://github.com/andrewhavck/dwolla-sdk-clojure"
  :license {:name "The MIT License (MIT)"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/data.json "0.2.5"]
                 [clj-http "0.9.2"]
                 [org.clojure/core.match "0.2.1"]]
  :profiles {:dev { :dependencies [[org.clojure/tools.namespace "0.2.4"]
                                  [org.clojure/java.classpath "0.2.2"]
                                  [midje "1.6.3"]
                                  [bultitude "0.2.6"]]
                     :plugins [[lein-midje "3.1.1"]]}})
