import Versions._
import sbt._

object Dependencies {
  lazy val zio = Seq(
    "dev.zio" %% "zio" % "1.0.12",
    "dev.zio" %% "zio-macros" % "1.0.12"
  )
  lazy val core = Seq(
    "io.scalaland" %% "chimney" % "0.6.1"
  )
  lazy val circe = Seq(
    "io.circe" %% "circe-core",
    "io.circe" %% "circe-generic",
    "io.circe" %% "circe-parser"
  ).map(_ % CirceVersion)

  lazy val httpServer = Seq(
    "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
    "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
    "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
    "de.heikoseeberger" %% "akka-http-circe" % AkkaHttpJsonVersion
  )
  lazy val tapir = Seq(
    "com.softwaremill.sttp.tapir" %% "tapir-core" % TapirVersion,
    "com.softwaremill.sttp.tapir" %% "tapir-akka-http-server" % TapirVersion
  )
  /* lazy val akka = Seq(
     "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
     "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
     "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion
   )*/

  lazy val testDeps = Seq(
    "org.scalatest" %% "scalatest" % "3.0.8" % Test,
    "org.scalamock" %% "scalamock" % "5.2.0" % Test
  )
}
