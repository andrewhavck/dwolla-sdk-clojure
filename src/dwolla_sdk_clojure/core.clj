(ns dwolla-sdk-clojure.core
  (:use [clojure.data.json :only [read-str]])
  (:require [clj-http.client :as client])
  (:import [java.net URLEncoder]))

(defn- account_info [token]
  (str "https://www.dwolla.com/oauth/rest/users/?oauth_token=" token))

(defn- basic_info [[client_id client_secret account_identifier]]
  (str "https://www.dwolla.com/oauth/rest/users/" account_identifier
       "?client_id=" client_id
       "&client_secret=" client_secret))

(defmulti api-get :end_point)
(defmethod api-get :account_info [req] (account_info  (:req req)))
(defmethod api-get :basic_info [req] (basic_info (:req req)))

(defn json? [resp] (= "application/json; charset=utf-8" ((:headers resp) "content-type")))

(defn response [resp] (merge {:Request-time (:request-time resp)
                              :Status (:status resp)}
                             (read-str (:body resp) :key-fn keyword)))

(defmulti api (fn [_ req & _] (map? req)))
(defmethod api false [end_point req]
  (response (client/get (api-get {:end_point end_point :req req}))))



 
