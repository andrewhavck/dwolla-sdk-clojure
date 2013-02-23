(ns dwolla-sdk-clojure.post-test
  (:use [dwolla-sdk-clojure.domain])
  (:use [dwolla-sdk-clojure.post])
  (:use [midje.sweet]))

(fact "Cancel creates correct url"
      (:url (api-post {:end_point :cancel :req {:oauth_token "dog" :request_id 1}})) =>
      (str domain "requests/1/cancel"))