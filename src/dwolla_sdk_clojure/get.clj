(ns dwolla-sdk-clojure.get
  (:use [dwolla-sdk-clojure.domain])
  (:use [clojure.data.json :only [read-str]]))

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

; Balance

(defn- balance [token]
  (str domain "balance?oauth_token=" token))

(defmulti api-get :end_point)

(defmethod api-get :balance [req] (balance (:req req)))

(defmethod api-get :account_info [req] (account_info  (:req req)))
(defmethod api-get :basic_info [req] (basic_info (:req req)))
(defmethod api-get :nearby [req] (nearby (:req req)))


