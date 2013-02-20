(ns dwolla-sdk-clojure.core
  (:import [java.net URLEncoder]))

(defn account_info [token]
  (str "https://www.dwolla.com/oauth/rest/users/?oauth_token=" token))

(defn basic_info [client_id client_secret account_identifier]
  (str "https://www.dwolla.com/oauth/rest/users/" account_identifier
       "?client_id=" client_id
       "client_secret=" client_secret))

(defn encode [url]
  (URLEncoder/encode url))