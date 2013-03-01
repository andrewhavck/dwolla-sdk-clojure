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

Below is an example send request:

```clojure
(dwolla/api :send {:oauth_token "" :pin "" :destinationId "" :amount ""})
```

Both get and post requests use the same interface.

Here is an account info request:

```clojure
(dwolla/api :account_info {:oauth_token "your oauth token"})
```

This is a nearby request:

```clojure
(dwolla/api :nearby {:client_id "" :client_secret "" :lat "" :long ""})
```

All results are returned as a Clojure map:

```clojure
{:Response nil, :Message "Invalid access token.", :Success false, :Request-time 321 :Status 200}
```

If the endpoint does not exist an error message is returned

```clojure
{:Response nil, :Message "Invalid endpoint." :Success false :Request-time 0 :Status nil}
```

## TODO: More examples


## License

Copyright © 2013 Dwolla

Released under the MIT License:
<http://www.opensource.org/licenses/mit-license.php>
