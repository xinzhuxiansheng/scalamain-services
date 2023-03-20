name := "zio-courses"
version := "0.1"
scalaVersion := "3.2.2"
organization := "dev.zio"

lazy val zioVersion = "2.0.10"

libraryDependencies ++= Seq(
  "dev.zio" %% "zio" % zioVersion,
  "dev.zio" %% "zio-test" % zioVersion,
  "dev.zio" %% "zio-test-sbt" % zioVersion,
  "dev.zio" %% "zio-streams" % zioVersion,
  "dev.zio" %% "zio-test-junit" % zioVersion
)

libraryDependencies += "dev.zio" %% "zio-cli" % "0.4.0"

testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")