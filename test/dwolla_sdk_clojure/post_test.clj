(ns dwolla-sdk-clojure.post-test
  (:use [dwolla-sdk-clojure.domain])
  (:use [dwolla-sdk-clojure.post])
  (:use [midje.sweet]))

(defn- make-post [end_point req] {:end_point end_point :req req})
(defn- get-post [post] (-> post :post :req))


;Funding Sources

(fact "Add includes token, acc number, routing number, acc type, and name"
      (let [req {:oauth_token "token"
                 :account_number "333333334"
                 :routing_number "123456789"
                 :account_type "Checking"
                 :name "Bank"}
            post (api-post (make-post :add_funding_source req))]
        (:url post) => (str domain "fundingsources/")
        (get-post post) => req))

(fact "Deposit includes token, funding_id, pin, and amount"
      (let [funding_id "id"
            req {:oauth_token "token"
                 :funding_id funding_id
                 :pin "1234"
                 :amount "10.00"}
        		post (api-post (make-post :deposit req))]
        (:url post) => (str domain "fundingsources/" funding_id "/deposit")
        (get-post post) => req))

(fact "Verify includes token, both deposits, and funding_id"
      (let [funding_id "id"
            req {:oauth_token "token"
                 :depost1 "0.05"
                 :deposit2 "0.01"
                 :funding_id funding_id}
        		post (api-post (make-post :verify req))]
        (:url post) => (str domain "fundingsources/" funding_id "/verify")
        (get-post post) => req))

(fact "Withdraw includes token, funding id, pin, and amount"
      (let [funding_id "id"
            req {:oauth_token "token"
                 :funding_id funding_id
                 :pin "1234"
                 :amount "10.00"}
        		post (api-post (make-post :withdraw req))]
        (:url post) => (str domain "fundingsources/" funding_id "/withdraw")
        (get-post post) => req))


;Requests

(fact "Cancel includes token, request id - request id in url"
      (let [request_id "1"
            req {:oauth_token "token"
                 :request_id request_id}
            post (api-post (make-post :cancel req))]
         (:url post) => (str domain "requests/" request_id "/cancel")
         (get-post post) => req))

(fact "Fulfill includes token, pin, amount, request id - request id in url"
      (let [request_id "1"
            req {:oauth_token "token"
                 :pin "1234"
                 :amount "10.00"
                 :request_id request_id}
            post (api-post (make-post :fulfill req))]
         (:url post) => (str domain "requests/" request_id "/fulfill")
         (get-post post) => req))

(fact "Request includes token, source id, amount"
      (let [req {:oauth_token "token"
                 :source_id "1234"
                 :amount "10.00"}
            post (api-post (make-post :request req))]
         (:url post) => (str domain "requests/")
         (get-post post) => req))
      
      
; Transactions

(fact "Send includes token, pin, destination id, and amount"
      (let [req {:oauth_token "token"
                 :pin "1234"
                 :destinationId "1"
                 :amount 20.00}
            post (api-post (make-post :send req))]
        (:url post) => (str domain "transactions/send")
        (get-post post) => req))


                                             