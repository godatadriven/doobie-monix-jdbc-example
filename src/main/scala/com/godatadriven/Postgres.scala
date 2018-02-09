package com.godatadriven

import doobie._
import doobie.implicits._
import doobie.hikari._
import doobie.hikari.implicits._

import monix.eval.Task

import scala.concurrent.duration._

object Postgres {
  private val DefaultTimeout = 10.seconds

  private val transactor: Task[HikariTransactor[Task]] =
    HikariTransactor.newHikariTransactor[Task](
      driverClassName = PostgresConfig.demo.driverClassName,
      url = PostgresConfig.demo.url,
      user = PostgresConfig.demo.user,
      pass = PostgresConfig.demo.password
    ).memoize

  def query[T](query: ConnectionIO[T]): Task[T] =
    for {
      xa <- transactor
      result <- query.transact(xa)
    } yield result

  def update(query: Update0): Task[Int] =
    for {
      xa <- transactor
      result <- query.run.transact(xa)
    } yield result

  def closeConnectionPool() = for {
    xa <- transactor
    _ <- xa.shutdown
  } yield ()

  def listCountriesByName(pattern: String, limit: Long = 10): Task[List[Country]] =
    query(Queries.selectCountryByName(pattern, limit).list)

  def listCitiesByCountry(code: Code, limit: Long = 10): Task[List[City]] =
    query(Queries.selectCitiesByCountry(code, limit).list)
}

case class PostgresConfig(url: String, user: String, password: String, driverClassName: String)
object PostgresConfig {
  val demo = PostgresConfig(
    driverClassName = "org.postgresql.Driver",
    url = "jdbc:postgresql://localhost/world",
    user="demo",
    password=""
  )
}
