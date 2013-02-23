(ns dwolla-sdk-clojure.post
  (:use [dwolla-sdk-clojure.domain])
  (:use [clojure.data.json :only [read-str write-str]]))

(defmulti api-post :end_point)
(defmethod api-post :cancel [req]
  (let [request_id (:request_id (:req req))]
    {:url (str domain "requests/" request_id "/cancel")
     :post req}))
