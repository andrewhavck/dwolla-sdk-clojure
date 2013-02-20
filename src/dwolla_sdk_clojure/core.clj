(ns dwolla-sdk-clojure.core)

(defn account_info [token]
  (str "https://www.dwolla.com/oauth/rest/users/?oauth_token=" token))
