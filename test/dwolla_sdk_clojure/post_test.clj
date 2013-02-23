(ns dwolla-sdk-clojure.post-test
  (:use [dwolla-sdk-clojure.domain])
  (:use [dwolla-sdk-clojure.post])
  (:use [midje.sweet]))

(fact "Cancel creates correct url"
      (:url (api-post {:end_point :cancel :req {:oauth_token "dog"
                                                :request_id 1}})) =>
                                                (str domain "requests/1/cancel"))

(fact "Send creates correct url"
      (:url (api-post {:end_point :send :req {:oauth_token "dog"
                                              :pin "1234"
                                              :destinationId "1"
                                              :amount 20.00}})) =>
                                              (str domain "transactions/send"))