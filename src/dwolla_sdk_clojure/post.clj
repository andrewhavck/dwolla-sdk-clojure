(ns dwolla-sdk-clojure.post
  (:use [dwolla-sdk-clojure.domain])
  (:use [clojure.data.json :only [read-str write-str]]))

;Funding Sources

(defn- add [req]
  {:url (str domain "fundingsources/")
   :post req})

;Requests

(defn- cancel [req]
  (let [request_id (:request_id (:req req))]
    {:url (str domain "requests/" request_id "/cancel")
     :post req}))

;Transactions

(defn- send- [req]
  {:url (str domain "transactions/send")
   :post req})

(defmulti api-post :end_point)
(defmethod api-post :add_funding_source [req] (add req))
(defmethod api-post :cancel [req] (cancel req))
(defmethod api-post :send [req] (send- req))
  
