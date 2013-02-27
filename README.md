# `dwolla-sdk-clojure` 

A simple Clojure library that wraps the [Dwolla API](http://developers.dwolla.com).

## Usage

The main api functionality is provided by the
`dwolla-sdk-clojure.core` namespace.

Require it in the REPL:

```clojure
(require '[dwolla-sdk-clojure.core :as dwolla])
```

Require it in your application:

```clojure
(ns my-app.core
  (:require [dwolla-sdk-clojure.core :as dwolla]))
```

The client uses an interface that requires an endpoint and message.

Below is an example send request (post):

```clojure
(dwolla/api :send {:oauth_token "" :pin "" :destinationId "" :amount ""})
```

Here is an account info request (get):

```clojure
(dwolla/api :account_info "your oauth token")
```

Multi parameter get request arguments are passed in as a vector:

```clojure
(dwolla/api :nearby ["client_id" "client_secret" "lat" "long"])
```

All results are returned as a Clojure map:

```clojure
{:Response nil, :Message "Invalid access token.", :Success false, :Request-time 321 :Status 200}
```

## TODO: More examples


## License

Copyright © 2013 Dwolla

Released under the MIT License:
<http://www.opensource.org/licenses/mit-license.php>
