(ns dwolla-sdk-clojure.domain)

(def domain "https://www.dwolla.com/oauth/rest/")
(defn uri [& args] (apply str domain args))