(ns dwolla-sdk-clojure.post
  (:use [dwolla-sdk-clojure.domain])
  (:use [clojure.data.json :only [read-str write-str]]))

;Funding Sources

(defn- get_funding_id [req] (-> req :req :funding_id))

(defn- add_funding_source [req]
  {:url (str domain "fundingsources/")
   :post req})

(defn- deposit [req]
  (verify_funding_req req "deposit"))

(defn- verify [req]
  (verify_funding_req req "verify"))

(defn- withdraw [req]
  (verify_funding_req req "withdraw"))


(defn- verify_funding_req [req end_point]
  (let [funding_id (get_funding_id req)]
    {:url (str domain "fundingsources/" funding_id "/" end_point)
     :post req}))

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
(defmethod api-post :add_funding_source [req] (add_funding_source req))
(defmethod api-post :deposit [req] (deposit req))
(defmethod api-post :verify [req] (verify req))
(defmethod api-post :withdraw [req] (withdraw req))
(defmethod api-post :cancel [req] (cancel req))
(defmethod api-post :send [req] (send- req))
(defmethod api-post :default [req] false)
  
