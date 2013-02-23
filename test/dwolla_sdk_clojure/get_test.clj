(ns dwolla-sdk-clojure.get-test
  (:use [dwolla-sdk-clojure.domain])
  (:use [dwolla-sdk-clojure.get])
  (:use [midje.sweet]))

(fact "Account info request includes correct token"
      (let [token "token"]
        (api-get {:end_point :account_info :req token})
         => (str domain "users/?oauth_token=" token)))

(fact "Basic info request includes correct params"
      (let [client_id "id"
            client_secret "secret"
            account_identifier "identifier"]
        (api-get {:end_point :basic_info :req [client_id client_secret account_identifier]}) =>
        (str domain "users/" account_identifier
             "?client_id=" client_id
             "&client_secret=" client_secret)))

(fact "Nearby request includes correct params"
      (let [client_id "id"
            client_secret "secret"
            lat "90"
            long "90"]
        (api-get {:end_point :nearby :req [client_id client_secret lat long]}) =>
        (str domain "users/nearby"
             "?client_id=" client_id
             "&client_secret=" client_secret
             "&latitude=" lat
             "&longitude=" long)))