(ns dwolla-sdk-clojure.core-test
  (:use [dwolla-sdk-clojure.domain])
  (:use [dwolla-sdk-clojure.core])
  (:use [midje.sweet]))

(fact "Create useful json response"
  (response {:request-time 500
             :status 200
             :body "{\"Success\":false,\"Message\":\"Invalid access token.\",\"Response\":null}"}) =>
             {:Request-time 500
              :Status 200
              :Message "Invalid access token."
              :Response nil
              :Success false})
