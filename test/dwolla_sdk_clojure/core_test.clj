(ns dwolla-sdk-clojure.core-test
  (:use [dwolla-sdk-clojure.core])
  (:use [midje.sweet]))

(fact "Account info request includes correct token"
  (let [token "token"]
    (account_info token) => (str "https://www.dwolla.com/oauth/rest/users/?oauth_token=" token)))

(fact "Basic info request includes correct params"
  (let [client_id "id"
        client_secret "secret"
        account_identifier "a@dwolla.com"]
    (basic_info client_id client_secret account_identifier) =>
    (str "https://www.dwolla.com/oauth/rest/users/" account_identifier
         "?client_id=" client_id
         "client_secret=" client_secret)))

(fact "Encode urls"
  (encode "test url") => "test+url")