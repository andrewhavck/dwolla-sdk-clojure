(ns dwolla-sdk-clojure.get
  (:use [dwolla-sdk-clojure.domain]
        [clojure.core.match :only [match]]
        [clojure.data.json :only [read-str]]))

(defn- parameterize [req parameter]
  (apply str (map #(if-let [x ((first %) req)] (str "&" (second %) "=" x) "") parameter)))

; Balance

(defn- balance [{token :oauth_token}]
  (uri "balance?oauth_token=" token))


; Funding sources

(defn- funding_sources_by_id [{token :oauth_token funding_id :funding_id}]
  (uri "fundingsources/" funding_id "?oauth_token=" token))

(defn- funding_sources_listing [{token :oauth_token}]
  (uri "fundingsources/?oauth_token=" token))


; Requests

(defn- request_by_id [{token :oauth_token request_id :request_id}]
  (uri "requests/" request_id "?oauth_token=" token))


(defn- pending [req] []
  (let [token (:oauth_token req)]
    (uri "requests/?oauth_token=" token
         (parameterize req [[:start_date "startDate"]
                            [:end_date "endDate"]
                            [:skip "skip"]
                            [:take "take"]]))))


; Transactions

(defn- transactions_by_id [{token :oauth_token t_id :transaction_id}]
  (uri "transactions/" t_id "?oauth_token=" token))

(defn- transactions [req]
  (let [token (:oauth_token req)]
    (uri "transactions/?oauth_token=" token
         (parameterize req [[:since_date "sinceDate"]
                            [:end_date "endDate"]
                            [:limit "limit"]
                            [:skip "skip"]
                            [:groupId "groupId"]
                            [:types "types"]]))))

(defn- transaction_stats [req]
  (let [token (:oauth_token req)]
    (uri "transactions/?oauth_token=" token
         (parameterize req [[:start_date "startDate"]
                            [:end_date "endDate"]
                            [:types "types"]]))))
; Users

(defn- account_info [{token :oauth_token}]
  (uri "users/?oauth_token=" token))

(defn- basic_info [{client_id          :client_id
                    client_secret      :client_secret
                    account_identifier :account_identifier}]
  (uri "users/" account_identifier "?client_id=" client_id
       "&client_secret=" client_secret))

(defn- contacts [req]
  (let [token (:oauth_token req)]
    (uri "contacts/?oauth_token=" token
         (parameterize req [[:search "search"]
                            [:types "types"]
                            [:limit "limit"]]))))

(defn- nearby [req]
  (let [client_id (:client_id req)]
    (uri "users/nearby?client_id=" client_id
         (parameterize req [[:client_secret "client_secret"]
                            [:latitude "latitude"]
                            [:longitude "longitude"]]))))

(defn api-get [msg]
  (let [req (:req msg)]
    (match [msg]
           [{:end_point :balance}] (balance req)
           [{:end_point :contacts}] (contacts req)
           [{:end_point :funding_sources_by_id}] (funding_sources_by_id req)
           [{:end_point :funding_sources_listing}] (funding_sources_listing req)
           [{:end_point :request_by_id}] (request_by_id req)
           [{:end_point :pending}] (pending req)
           [{:end_point :transactions_by_id}] (transactions_by_id req)
           [{:end_point :transactions}] (transactions req)
           [{:end_point :transaction_stats}] (transaction_stats req)
           [{:end_point :account_info}] (account_info req)
           [{:end_point :basic_info}] (basic_info req)
           [{:end_point :nearby}] (nearby req)
           :else false)))
