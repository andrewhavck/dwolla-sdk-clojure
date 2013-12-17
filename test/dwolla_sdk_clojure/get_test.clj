(ns dwolla-sdk-clojure.get-test
  (:use [dwolla-sdk-clojure.domain]
        [dwolla-sdk-clojure.get]
        [midje.sweet]))

; Balance

(fact "Balance has token"
      (let [token "token"]
        (api-get {:end_point :balance :req {:oauth_token token}}) =>
        (str domain "balance?oauth_token=" token)))


;Funding sources

(fact "Funding sources has token and funding id"
      (let [token "token"
            id "funding id"]
        (api-get {:end_point :funding_sources_by_id 
                  :req {:oauth_token token :funding_id id}}) =>
      	 (str domain "fundingsources/" id "?oauth_token=" token)))

(fact "Funding sources listing has token"
      (let [token "token"]
        (api-get {:end_point :funding_sources_listing :req {:oauth_token token}}) =>
        (str domain "fundingsources/?oauth_token=" token)))


; Request

(fact "Request by id has token and request id"
      (let [token "token"
            request_id "id"]
        (api-get {:end_point :request_by_id 
                  :req {:oauth_token token :request_id request_id}}) =>
        (str domain "requests/" request_id "?oauth_token=" token)))

(fact "Pending has token"
      (let [token "token"]
        (api-get {:end_point :pending :req {:oauth_token token}}) =>
        (str domain "requests/?oauth_token=" token)))


; Transactions

(fact "Transactions by id has token"
  (let [token "token" 
        transaction_id "id"]
    (api-get {:end_point :transactions_by_id 
              :req {:oauth_token token :transaction_id transaction_id}}) =>
    (str domain "transactions/" transaction_id "?oauth_token=" token)))

; Users

(fact "Account info request has token"
      (let [token "token"]
        (api-get {:end_point :account_info :req {:oauth_token token}}) => 
        (str domain "users/?oauth_token=" token)))

(fact "Basic info request has client id, client secret, and account identifier"
      (let [client_id "id"
            client_secret "secret"
            account_identifier "identifier"]
        (api-get {:end_point :basic_info 
                  :req {:client_id client_id 
                        :client_secret client_secret 
                        :account_identifier account_identifier}}) =>
        (str domain "users/" account_identifier
             "?client_id=" client_id
             "&client_secret=" client_secret)))

(fact "Nearby request has client id, client secret, lat, and long"
      (let [client_id "id"
            client_secret "secret"
            lat "90"
            long "90"]
        (api-get {:end_point :nearby 
                  :req {:client_id client_id 
                        :client_secret client_secret
                        :lat lat 
                        :long long}}) =>
        (str domain "users/nearby"
             "?client_id=" client_id
             "&client_secret=" client_secret
             "&latitude=" lat
             "&longitude=" long)))
