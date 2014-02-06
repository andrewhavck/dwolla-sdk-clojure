(ns dwolla-sdk-clojure.post
  (:use [dwolla-sdk-clojure.domain]
        [clojure.core.match :only [match]]
        [clojure.data.json :only [read-str write-str]]))

(defn- post_msg [req type end_point id]
  {:url  (uri type "/" id "/" end_point)
   :post req})


;Funding Sources

(defn- funding_post [req end_point]
  (post_msg req "fundingsources" end_point (-> req :req :funding_id)))

(defn- add_funding_source [req]
  {:url  (uri "fundingsources/")
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
  {:url  (uri "requests/")
   :post req})


;Transactions

(defn- send- [req]
  {:url  (uri "transactions/send")
   :post req})

(defn api-post [req]
  (match [req]
         [{:end_point :add_funding_source}] (add_funding_source req)
         [{:end_point :deposit}] (deposit req)
         [{:end_point :verify}] (verify req)
         [{:end_point :withdraw}] (withdraw req)
         [{:end_point :cancel}] (cancel req)
         [{:end_point :fulfill}] (fulfill req)
         [{:end_point :request}] (request req)
         [{:end_point :send}] (send- req)
         :else false))
