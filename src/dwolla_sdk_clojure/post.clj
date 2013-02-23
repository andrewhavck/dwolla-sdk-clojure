(ns dwolla-sdk-clojure.post
  (:use [dwolla-sdk-clojure.domain])
  (:use [clojure.data.json :only [read-str write-str]]))

(defn- cancel [req]
  (let [request_id (:request_id (:req req))]
    {:url (str domain "requests/" request_id "/cancel")
     :post req}))

(defn- send- [req]
  {:url (str domain "transactions/send")
   :post req})

(defmulti api-post :end_point)
(defmethod api-post :cancel [req] (cancel req))
(defmethod api-post :send [req] (send- req))
  
