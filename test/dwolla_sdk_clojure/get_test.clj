(ns dwolla-sdk-clojure.get-test
  (:use [dwolla-sdk-clojure.domain])
  (:use [dwolla-sdk-clojure.get])
  (:use [midje.sweet]))

; Balance

(fact "Balance"
      (let [token "token"]
        (api-get {:end_point :balance
                  :req       {:oauth_token token}}) =>
        (uri "balance?oauth_token=" token)))


;Funding sources

(fact "Funding sources by id"
      (let [token "token"
            id "funding id"]
        (api-get {:end_point :funding_sources_by_id
                  :req       {:oauth_token token :funding_id id}}) =>
        (uri "fundingsources/" id "?oauth_token=" token)))

(fact "Funding sources listing"
      (let [token "token"]
        (api-get {:end_point :funding_sources_listing
                  :req       {:oauth_token token}}) =>
        (uri "fundingsources/?oauth_token=" token)))


; Request

(fact "Request by id"
      (let [token "token"
            request_id "id"]
        (api-get {:end_point :request_by_id
                  :req       {:oauth_token token :request_id request_id}}) =>
        (uri "requests/" request_id "?oauth_token=" token)))

(fact "Pending"
      (let [token "token"
            startDate "02-02-2013"
            endDate "02-02-2014"
            skip "20"
            limit "10"]
        (api-get {:end_point :pending
                  :req       {:oauth_token token
                              :start_date  startDate
                              :end_date    endDate
                              :skip        skip
                              :take        take}}) =>
        (uri
          "requests/?oauth_token=" token
          "&startDate=" startDate
          "&endDate=" endDate
          "&skip=" skip
          "&take=" take)))


; Transactions

(fact "Transactions by id"
      (let [token "token"
            transaction_id "id"]
        (api-get {:end_point :transactions_by_id
                  :req       {:oauth_token token :transaction_id transaction_id}}) =>
        (uri "transactions/" transaction_id "?oauth_token=" token)))


(fact "Transaction listing"
      (let [token "token"
            sinceDate "01-01-2010"
            endDate "01-01-2010"
            limit "10"
            skip "20"
            groupId "myGroup"
            types "money_sent,deposit"]
        (api-get {:end_point :transactions
                  :req       {:oauth_token token
                              :since_date  sinceDate
                              :end_date    endDate
                              :limit       limit
                              :skip        skip
                              :groupId     groupId
                              :types       types}}) =>
        (uri
          "transactions/?oauth_token=" token
          "&sinceDate=" sinceDate
          "&endDate=" endDate
          "&limit=" limit
          "&skip=" skip
          "&groupId=" groupId
          "&types=" types)))

(fact "Transaction statistics"
      (let [token "token"
            types "money_sent,deposit"
            startDate "01-01-2010"
            endDate "01-01-2010"]
        (api-get {:end_point :transaction_stats
                  :req       {:oauth_token token
                              :start_date  startDate
                              :end_date    endDate
                              :types       types}}) =>
        (uri
          "transactions/?oauth_token=" token
          "&startDate=" startDate
          "&endDate=" endDate
          "&types=" types)))
; Users

(fact "Account info"
      (let [token "token"]
        (api-get {:end_point :account_info
                  :req       {:oauth_token token}}) =>
        (uri "users/?oauth_token=" token)))

(fact "Basic info"
      (let [client_id "id"
            client_secret "secret"
            account_identifier "identifier"]
        (api-get {:end_point :basic_info
                  :req       {:client_id          client_id
                              :client_secret      client_secret
                              :account_identifier account_identifier}}) =>
        (uri "users/" account_identifier
             "?client_id=" client_id
             "&client_secret=" client_secret)))

(fact "Contacts"
      (let [token "token"
            search "term"
            types "personal,business"
            limit 100]
        (api-get {:end_point :contacts
                  :req       {:oauth_token token
                              :search      search
                              :types       types
                              :limit       limit}}) =>
        (uri "contacts/?oauth_token=" token
             "&search=" search
             "&types=" types
             "&limit=" limit)))

(fact "Nearby"
      (let [client_id "id"
            client_secret "secret"
            lat "90"
            long "90"]
        (api-get {:end_point :nearby
                  :req       {:client_id     client_id
                              :client_secret client_secret
                              :latitude      lat
                              :longitude     long}}) =>
        (uri "users/nearby"
             "?client_id=" client_id
             "&client_secret=" client_secret
             "&latitude=" lat
             "&longitude=" long)))
