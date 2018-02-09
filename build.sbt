import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.godatadriven",
      scalaVersion := "2.12.4",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "doobie-monix-example",
    libraryDependencies ++= List(
      doobieCore, doobieHikari, doobiePostgres, monix, doobieScalatest, scalaTest, logback, scalaLogging
    )
  )
