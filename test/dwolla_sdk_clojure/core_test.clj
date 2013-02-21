(ns dwolla-sdk-clojure.core-test
  (:use [dwolla-sdk-clojure.core])
  (:use [midje.sweet]))

(fact "Account info request includes correct token"
      (let [token "token"]
        (get-api-req {:type :account_info :msg token})
         => (str "https://www.dwolla.com/oauth/rest/users/?oauth_token=" token)))

(fact "Basic info request includes correct params"
  (let [client_id "id"
        client_secret "secret"
        account_identifier "identifier"]
    (get-api-req {:type :basic_info :msg [client_id client_secret account_identifier]}) =>
    (str "https://www.dwolla.com/oauth/rest/users/" account_identifier
         "?client_id=" client_id
         "&client_secret=" client_secret)))

(fact "json? checks that the content-type is json"
      {:headers {"content-type" "application/json; charset=utf-8"}} => json?)