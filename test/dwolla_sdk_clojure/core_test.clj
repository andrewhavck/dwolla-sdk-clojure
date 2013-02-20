(ns dwolla-sdk-clojure.core-test
  (:use [dwolla-sdk-clojure.core])
  (:use [midje.sweet]))

(fact "Account info request includes correct token"
  (let [token "token"]
   (account_info token) => (str "https://www.dwolla.com/oauth/rest/users/?oauth_token=" token)))