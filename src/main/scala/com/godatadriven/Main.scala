package com.godatadriven

import com.typesafe.scalalogging.LazyLogging
import monix.execution.Scheduler

import scala.concurrent.Await
import scala.concurrent.duration._

object DoobieMonixDemo extends App with LazyLogging {
  implicit val scheduler = Scheduler.io()

  val transaction = for {
    neCountries <- Postgres.listCountriesByName(pattern = "ne%")
    anCountries <- Postgres.listCountriesByName(pattern = "%an")
    dutchCities <- Postgres.listCitiesByCountry(code = Code("NLD"))
    _ <- Postgres.closeConnectionPool()
  } yield (neCountries, anCountries, dutchCities)

  val task = transaction.map{ case (neCountries, anCountries, dutchCities) =>
    val neStr = neCountries.mkString("\n\t")
    val anStr = anCountries.mkString("\n\t")
    val nldStr = dutchCities.mkString("\n\t")
    logger.info(s"Countries starting with 'NE':\n\t$neStr")
    logger.info(s"Countries ending with 'AN':\n\t$anStr")
    logger.info(s"Cities in the Netherlands:\n\t$nldStr")
  }

  Await.result(task.runAsync, 5 seconds)
}
