ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

name := "chapter-structure"

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-Xlint",
  "-Ywarn-unused",
  "-Ywarn-dead-code",
  "-feature",
  "-language:_"
)

libraryDependencies ++= {
  val akkaVersion = "2.6.19"
  Seq(
    "com.typesafe.akka" %%  "akka-actor"   % akkaVersion,
    "com.typesafe.akka" %%  "akka-slf4j"   % akkaVersion,
    "com.typesafe.akka" %%  "akka-testkit" % akkaVersion  % "test",
    "org.scalatest"     %%  "scalatest"    % "3.2.12"      % "test"
  )
}