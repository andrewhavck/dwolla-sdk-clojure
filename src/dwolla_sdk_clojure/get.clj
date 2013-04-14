(ns dwolla-sdk-clojure.get
  (:use [dwolla-sdk-clojure.domain])
  (:use [clojure.data.json :only [read-str]]))


; Balance

(defn- balance [{token :oauth_token}]
  (uri "balance?oauth_token=" token))


; Funding sources

(defn- funding_sources_by_id [{token :oauth_token funding_id :funding_id}]
  (uri "fundingsources/" funding_id "?oauth_token=" token))

(defn- funding_sources_listing [{token :oauth_token}]
  (uri "fundingsources/?oauth_token=" token))


; Requests

(defn- request_by_id [{token :oauth_token request_id :request_id}]
  (uri "requests/" request_id "?oauth_token=" token))

(defn- pending [{token :oauth_token}]
  (uri "requests/?oauth_token=" token))

; Transactions

(defn- 
  transactions_by_id_client
  [{client_id :client_id client_secret :client_secret transaction_id :transaction_id}]
      (uri "transactions/" transaction_id "?client_id=" client_id "client_secret=" client_secret))

(defn- 
  transactions_by_id_token
  [{token :oauth_token transaction_id :transaction_id}]
     (uri "transactions/" transaction_id "?oauth_token=" token))

(defmulti transactions_by_id :oauth_token)
(defmethod transactions_by_id nil? [req] (transactions_by_id_client req))
(defmethod transactions_by_id :default [req] (transactions_by_id_token req))

; Users

(defn- account_info [{token :oauth_token}]
  (uri "users/?oauth_token=" token))

(defn- basic_info [{client_id :client_id 
                    client_secret :client_secret
                    account_identifier :account_identifier}]
  (uri "users/" account_identifier "?client_id=" client_id
       "&client_secret=" client_secret))

(defn- nearby [{client_id :client_id
                client_secret :client_secret
                lat :lat 
                long :long}]
  (uri "users/nearby?"
       "client_id=" client_id
       "&client_secret=" client_secret
       "&latitude=" lat
       "&longitude=" long))

(defmulti api-get :end_point)
(defmethod api-get :balance [req] (balance (:req req)))
(defmethod api-get :funding_sources_by_id [req] (funding_sources_by_id (:req req)))
(defmethod api-get :funding_sources_listing [req] (funding_sources_listing (:req req)))
(defmethod api-get :request_by_id [req] (request_by_id (:req req)))
(defmethod api-get :pending [req] (pending (:req req)))
(defmethod api-get :transactions_by_id [req] (transactions_by_id  (:req req)))
(defmethod api-get :account_info [req] (account_info (:req req)))
(defmethod api-get :basic_info [req] (basic_info (:req req)))
(defmethod api-get :nearby [req] (nearby (:req req)))
(defmethod api-get :default [req] false)