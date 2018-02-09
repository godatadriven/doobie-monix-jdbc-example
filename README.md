Doobie + Monix Example
=======================

This repository contains a small example sbt project to demonstrate how to combine [Doobie](http://tpolecat.github.io/doobie/) (a principled JDBC layer) and [Monix](https://monix.io/) (asynchronous programming in Scala) to obtain fast, concise and typechecked JDBC access in your Scala project.

Getting started
===============

The project contains the configuration to build a Postgres docker image that includes some sample data. To create the docker image apply the following in your shell:

```
cd database
docker build -t docker-postgres:doobie
docker run -p 5432:5432 docker-postgres:doobie
```

Once the docker-postgres container is running, you can run the example application (`sbt run`) and run the unit tests (`sbt test`)
