name := "routing"

version := "0.1.0-SNAPSHOT"

organization := "com.manning"

ThisBuild / scalaVersion := "2.13.10"

libraryDependencies ++= {
  val akkaVersion = "2.6.19"
  Seq(
    "com.typesafe.akka" %% "akka-actor"   % akkaVersion,
    "com.typesafe.akka" %% "akka-remote"  % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j"   % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion  % "test",
    "org.scalatest"     %% "scalatest"    % "3.2.12"      % "test"
  )
}
