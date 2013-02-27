(ns dwolla-sdk-clojure.get
  (:use [dwolla-sdk-clojure.domain])
  (:use [clojure.data.json :only [read-str]]))

; Balance

(defn- balance [token]
  (str domain "balance?oauth_token=" token))

;Requests

(defn- request_by_id [[token request_id]]
  (str domain "requests/" request_id "?oauth_token=" token))

(defn- pending [token]
  (str domain "requests/?oauth_token=" token))

; Users

(defn- account_info [token]
  (str domain "users/?oauth_token=" token))

(defn- basic_info [[client_id client_secret account_identifier]]
  (str domain "users/" account_identifier "?client_id=" client_id
       "&client_secret=" client_secret))

(defn- nearby [[client_id client_secret lat long]]
  (str domain "users/nearby?"
       "client_id=" client_id
       "&client_secret=" client_secret
       "&latitude=" lat
       "&longitude=" long))

(defmulti api-get :end_point)
(defmethod api-get :balance [req] (balance (:req req)))
(defmethod api-get :request_by_id [req] (request_by_id (:req req)))
(defmethod api-get :account_info [req] (account_info  (:req req)))
(defmethod api-get :basic_info [req] (basic_info (:req req)))
(defmethod api-get :nearby [req] (nearby (:req req)))
(defmethod api-get :pending [req] (pending (:req req)))



