ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "crawler"
  )

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % "2.6.19",
  "io.spray" %% "spray-json" % "1.3.5",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.2.9",
  "com.typesafe.akka" %% "akka-http" % "10.2.9",
  "com.typesafe.akka" %% "akka-actor" % "2.6.19",
  "org.scala-lang" % "scala-library" % "2.13.8",
  "org.scalatest" %% "scalatest" % "3.0.8"  % Test,
  "com.typesafe.akka" %% "akka-http-testkit" % "10.2.9" % Test,
  "org.mockito" % "mockito-all" % "2.0.2-beta" % Test
)

