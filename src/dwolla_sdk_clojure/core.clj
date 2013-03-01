(ns dwolla-sdk-clojure.core
  (:use [clojure.data.json :only [read-str write-str]])
  (:use [dwolla-sdk-clojure.post])
  (:use [dwolla-sdk-clojure.get])
  (:require [clj-http.client :as client]))

(defn create-resp [resp] 
  (merge {:Request-time (:request-time resp)
          :Status (:status resp)}
          (read-str (:body resp) :key-fn keyword)))

(defn- do-get [request] (client/get request))

(defn- do-post [request]
  (client/post (:url request)
               {:body (-> request :post :req write-str)
                :content-type :json}))

(defn api-req [msg] (remove nil? 
                        (map (fn [call client] 
                           (if-let [req (call msg)]
                                    (client req)))
                                    [api-get api-post] [do-get do-post])))

(defn response [resp]
  (if (empty? resp)
    {:Response nil, :Message "Invalid endpoint." :Success false :Request-time 0 :Status nil}
    (apply create-resp resp)))

(defn api [end_point req] (response (api-req {:end_point end_point :req req})))