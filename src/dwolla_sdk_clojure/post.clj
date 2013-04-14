(ns dwolla-sdk-clojure.post
  (:use [dwolla-sdk-clojure.domain])
  (:use [clojure.data.json :only [read-str write-str]]))

(defn- post_msg [req type end_point id]
    {:url (uri type "/" id "/" end_point)
     :post req})


;Funding Sources

(defn- funding_post [req end_point]
  (post_msg req "fundingsources" end_point (-> req :req :funding_id)))

(defn- add_funding_source [req]
  {:url (uri "fundingsources/")
   :post req})

(defn- deposit [req]
  (funding_post req "deposit"))

(defn- verify [req]
  (funding_post req "verify"))

(defn- withdraw [req]
  (funding_post req "withdraw"))


;Requests

(defn- request_post [req end_point]
  (post_msg req "requests" end_point (-> req :req :request_id)))

(defn- cancel [req]
  (request_post req "cancel"))

(defn- fulfill [req]
  (request_post req "fulfill"))

(defn- request [req]
  {:url (uri "requests/")
   :post req})


;Transactions

(defn- send- [req]
  {:url (uri "transactions/send")
   :post req})

(defmulti api-post :end_point)
(defmethod api-post :add_funding_source [req] (add_funding_source req))
(defmethod api-post :deposit [req] (deposit req))
(defmethod api-post :verify [req] (verify req))
(defmethod api-post :withdraw [req] (withdraw req))
(defmethod api-post :cancel [req] (cancel req))
(defmethod api-post :fulfill [req] (fulfill req))
(defmethod api-post :request [req] (request req))
(defmethod api-post :send [req] (send- req))
(defmethod api-post :default [req] false)
  
