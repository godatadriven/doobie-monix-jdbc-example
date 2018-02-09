import sbt._

object Dependencies {
  val doobieVersion = "0.5.0"

  val doobieCore      = "org.tpolecat"                %% "doobie-core"        % doobieVersion
  val doobieHikari    = "org.tpolecat"                %% "doobie-hikari"      % doobieVersion
  val doobiePostgres  = "org.tpolecat"                %% "doobie-postgres"    % doobieVersion
  val monix           = "io.monix"                    %% "monix"              % "2.3.3"

  val logback         = "ch.qos.logback"              % "logback-classic"     % "1.2.3"
  val scalaLogging    = "com.typesafe.scala-logging"  %% "scala-logging"      % "3.7.2"

  val doobieScalatest = "org.tpolecat"                %% "doobie-scalatest"   % doobieVersion   % Test
  val scalaTest       = "org.scalatest"               %% "scalatest"          % "3.0.4"         % Test
}
