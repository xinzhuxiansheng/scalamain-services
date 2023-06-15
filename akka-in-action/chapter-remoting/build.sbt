ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

name := "chapter-remoting"

libraryDependencies ++= {
  val akkaVersion = "2.6.19"
  val akkaHttpVersion = "10.2.9"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,

    "com.typesafe.akka" %% "akka-remote" % akkaVersion,
    "com.typesafe.akka" %% "akka-multi-node-testkit" % akkaVersion % "test",

    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
    "org.scalatest" %% "scalatest" % "3.2.12",
    "com.typesafe.akka" %% "akka-http-core" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
    "ch.qos.logback" % "logback-classic" % "1.1.6",
    "com.typesafe.akka" %% "akka-multi-node-testkit" % akkaVersion,
    "com.typesafe.akka" %% "akka-cluster" % akkaVersion
  )
}

// Assembly settings
mainClass in assembly := Some("com.goticks.SingleNodeMain")

assemblyJarName in assembly := "goticks-server.jar"

sourceDirectories in Test += baseDirectory.value / "multi-jvm"