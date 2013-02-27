(ns dwolla-sdk-clojure.post-test
  (:use [dwolla-sdk-clojure.domain])
  (:use [dwolla-sdk-clojure.post])
  (:use [midje.sweet]))

(defn- get-post [post] (-> post :post :req))

;Funding Sources

(fact "Add includes token, acc number, routing number, acc type, and name"
      (let [req {:oauth_token "token"
                 :account_number "333333334"
                 :routing_number "123456789"
                 :account_type "Checking"
                 :name "Bank"}
            post (api-post {:end_point
                            :add_funding_source
                            :req req})]
        (:url post) => (str domain "fundingsources/")
        (get-post post) => req))
      

;Requests

(fact "Cancel includes token, request id - request id in url"
      (let [request_id "1"
            req {:oauth_token "token"
                 :request_id request_id}
            post (api-post {:end_point
                            :cancel
                            :req req})]
         (:url post) => (str domain "requests/" request_id "/cancel")
         (get-post post) => req))
      
; Transactions

(fact "Send includes token, pin, destination id, and amount"
      (let [req {:oauth_token "token"
                 :pin "1234"
                 :destinationId "1"
                 :amount 20.00}
            post (api-post {:end_point :send :req req})]
        (:url post) => (str domain "transactions/send")
        (get-post post) => req))


                                             