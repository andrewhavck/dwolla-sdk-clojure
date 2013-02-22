(ns dwolla-sdk-clojure.core-test
  (:use [dwolla-sdk-clojure.core])
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

(fact "json? checks that the content-type is json"
  {:headers {"content-type" "application/json; charset=utf-8"}} => json?)

(fact "Create useful json response"
  (response {:request-time 500
             :status 200
             :body "{\"Success\":false,\"Message\":\"Invalid access token.\",\"Response\":null}"}) =>
             {:Request-time 500
              :Status 200
              :Message "Invalid access token."
              :Response nil
              :Success false})

(fact "Cancel creates correct url"
      (:url (api-post {:end_point :cancel :req {:oauth_token "dog" :request_id 1}})) =>
      (str domain "requests/1/cancel"))