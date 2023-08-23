ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.11"

lazy val root = (project in file("."))
  .settings(
    name := "scalamain-http4s"
  ).enablePlugins(AssemblyPlugin)

libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-blaze-server" % "0.21.25",
  "org.http4s" %% "http4s-dsl" % "0.21.25",
  "org.http4s" %% "http4s-circe" % "0.21.25",
  "io.circe" %% "circe-generic" % "0.13.0",
  "io.circe" %% "circe-core" % "0.13.0",
  "io.circe" %% "circe-parser" % "0.13.0",
  "org.typelevel" %% "cats-effect" % "2.5.1"
)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs@_*) => MergeStrategy.discard
  case x => MergeStrategy.first
}
