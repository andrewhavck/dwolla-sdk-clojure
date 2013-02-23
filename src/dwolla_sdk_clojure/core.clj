(ns dwolla-sdk-clojure.core
  (:use [clojure.data.json :only [read-str write-str]])
  (:use [dwolla-sdk-clojure.post])
  (:use [dwolla-sdk-clojure.get])
  (:require [clj-http.client :as client]))

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
