(ns dwolla-sdk-clojure.core
  (:import [java.net URLEncoder]))

(defn- account_info [token]
  (str "https://www.dwolla.com/oauth/rest/users/?oauth_token=" token))

(defn- basic_info [[client_id client_secret account_identifier]]
  (str "https://www.dwolla.com/oauth/rest/users/" account_identifier
       "?client_id=" client_id
       "client_secret=" client_secret))

(defn encode [url] (URLEncoder/encode url))

(defmulti #^{:private true} get-api-req :type)
(defmethod get-api-req :account_info [req] (account_info  (:msg req)))
(defmethod get-api-req :basic_info [req] (basic_info (:msg req)))

(defn req [request] (encode (get-api-req request)))



 
