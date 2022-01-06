import Dependencies._

ThisBuild / scalaVersion     := "2.13.7"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "ru.shaldnikita"
ThisBuild / organizationName := "biba"
Compile / scalacOptions += "-Ymacro-annotations"
lazy val root = (project in file("."))
  .settings(
    name := "biba",
    libraryDependencies ++= zio ++ testDeps ++ tapir ++ httpServer ++ circe ++ core
  )

