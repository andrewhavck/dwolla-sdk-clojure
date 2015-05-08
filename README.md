# `dwolla-sdk-clojure`

[![Build Status](https://travis-ci.org/andrewhavck/dwolla-sdk-clojure.png?branch=master)](https://travis-ci.org/andrewhavck/dwolla-sdk-clojure)

A simple Clojure library that wraps the [Dwolla API](http://developers.dwolla.com).

## Installation

`dwolla-sdk-clojure` is available as a Maven artifact from
[Clojars](http://clojars.org/dwolla-sdk-clojure):

```clojure
[dwolla-sdk-clojure "0.1.2"]
```

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

The client is a thin interface that requires only an endpoint and message

```clojure
;An example send POST request:
(dwolla/api :send {:oauth_token "" :pin "" :destinationId "" :amount ""})

;An account info GET request:
(dwolla/api :account_info {:oauth_token ""})

;A nearby GET request:
(dwolla/api :nearby {:client_id "" :client_secret "" :lat "" :long ""})
```

All results are returned as a map:

```clojure
{:Response nil, :Message "Invalid access token.", :Success false, :Request-time 321 :Status 200}

;If the endpoint does not exist a generic error message is returned
{:Response nil, :Message "Invalid endpoint." :Success false :Request-time 0 :Status nil}
```

## Support

- Dwolla API | api@dwolla.com | [@DwollaAPI](https://twitter.com/DwollaAPI)
- Andrew Hauck | andrew@dwolla.com | [@andrewhavck](https://twitter.com/andrewhavck)
- Fredrick Galoso | fred@dwolla.com | [@wayoutmind](https://twitter.com/wayoutmind)

## License

Copyright © 2015 Dwolla

Released under the MIT License:
<http://www.opensource.org/licenses/mit-license.php>
