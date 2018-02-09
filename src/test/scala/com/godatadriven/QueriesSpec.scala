package com.godatadriven

import doobie._
import doobie.scalatest.imports._
import cats.effect.IO
import org.scalatest._

class QueriesSpec extends WordSpec with Matchers with IOChecker {

  val transactor = Transactor.fromDriverManager[IO](
    PostgresConfig.demo.driverClassName,
    PostgresConfig.demo.url,
    PostgresConfig.demo.user,
    PostgresConfig.demo.password
  )

  "check selectCountryByName" in {
    check(Queries.selectCountryByName(pattern = "%NE%", limit = 10))
  }

  "check selectCitiesByCountry" in {
    check(Queries.selectCitiesByCountry(countryCode = Code("NLD"), limit = 10))
  }
}
