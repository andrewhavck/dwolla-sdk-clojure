(ns dwolla-sdk-clojure.core
  (:use [clojure.data.json :only [read-str write-str]])
  (:use [dwolla-sdk-clojure.post])
  (:use [dwolla-sdk-clojure.get])
  (:require [clj-http.client :as client]))

(defn response [resp] (merge {:Request-time (:request-time resp)
                              :Status (:status resp)}
                             (read-str (:body resp) :key-fn keyword)))

(defn- get [call] (client/get (api-get call)))
(defn- post [call]
  (let [request (api-post call)]
    (client/post (:url request)
                 {:body (-> request :post :req write-str)
                  :content-type :json})))

(defn api-req [call] (take-while false) [get post])

(defn api [end_point req] (response (api-req {:end_point end_point :req req})))
