# online-code-executor-example

Online code executor(like [paiza](https://paiza.io/ja/projects/new) and [Online PHP functions](http://sandbox.onlinephpfunctions.com/)) example implementation in clojure.

## Development

To start external componentse for development, run

    docker-compose up -d

To start application on your REPL, run

    (go)

You will get 200 OK from

    http://localhost:3000/langs/php

## Deployment

TODO: add k8s deployment script.

:construction:
