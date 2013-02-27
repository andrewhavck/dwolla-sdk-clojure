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

The client functions off a simple interface that only requires the desired endpoint and request.

Below is an example send request (post):

```clojure
(dwolla/api :send {:oauth_token "" :pin "" :destinationId "" :amount ""})
```

Here is a simple account info request (get):

```clojure
(dwolla/api :account_info "your oauth token")
```

Multi parameter get requests have their parameters passed in via a vector:

```clojure
(dwolla/api :nearby ["client_id" "client_secret" "lat" "long"])
```

All results are returned as simple Clojure maps:

```clojure
{:Response nil, :Message "Invalid access token.", :Success false, :Request-time 321 :Status 200}
```

## TODO: More examples


## License

Copyright © 2013 Dwolla

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the 'Software'), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED 'AS IS', WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
