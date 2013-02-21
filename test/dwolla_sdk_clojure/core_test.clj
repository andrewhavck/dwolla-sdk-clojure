(ns dwolla-sdk-clojure.core-test
  (:use [dwolla-sdk-clojure.core])
  (:use [midje.sweet]))

(fact "Account info request includes correct token"
      (let [token "token"]
        (req {:type :account_info :msg token})
         => (str "https%3A%2F%2Fwww.dwolla.com%2Foauth%2Frest%2Fusers%2F%3Foauth_token%3D" token)))

(fact "Basic info request includes correct params"
  (let [client_id "id"
        client_secret "secret"
        account_identifier "identifier"]
    (req {:type :basic_info :msg [client_id client_secret account_identifier]}) =>
    (str "https%3A%2F%2Fwww.dwolla.com%2Foauth%2Frest%2Fusers%2F" account_identifier
         "%3Fclient_id%3D" client_id
         "client_secret%3D" client_secret)))

(fact "Encode urls"
      (encode "test url") => "test+url")
