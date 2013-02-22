(ns dwolla-sdk-clojure.core
  (:use [clojure.data.json :only [read-str write-str]])
  (:require [clj-http.client :as client])
  (:import [java.net URLEncoder]))

(def domain "https://www.dwolla.com/oauth/rest/")

(defn- account_info [token]
  (str domain "users/?oauth_token=" token))

(defn- basic_info [[client_id client_secret account_identifier]]
  (str domain "users/" account_identifier "?client_id=" client_id
       "&client_secret=" client_secret))

(defmulti api-get :end_point)
(defmethod api-get :account_info [req] (account_info  (:req req)))
(defmethod api-get :basic_info [req] (basic_info (:req req)))

(defmulti api-post :end_point)
(defmethod api-post :cancel [req]
  (let [request_id (:request_id (:req req))]
    {:url (str domain "requests/" request_id "/cancel")
     :post req}))

(defn json? [resp] (= "application/json; charset=utf-8" ((:headers resp) "content-type")))

(defn response [resp] (merge {:Request-time (:request-time resp)
                              :Status (:status resp)}
                             (read-str (:body resp) :key-fn keyword)))

(defmulti api-req (fn [call] (map? (:req call))))
(defmethod api-req false [call] (client/get (api-get call)))
(defmethod api-req true [call]
  (let [request (api-post call)]
    (client/post (:url request)
                 {:body (write-str (:post request))
                  :content-type :json})))

(defn api [end_point req] (response (api-req {:end_point end_point :req req})))



 
