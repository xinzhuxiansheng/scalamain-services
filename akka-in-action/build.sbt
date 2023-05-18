ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "akka-in-action"
  )

lazy val test = (project in file("chapter-testdriven"))
  .settings(
    name := "chapter-testdriven"
  )

lazy val up = (project in file("chapter-up-and-running"))
  .settings(
    name := "chapter-up-and-running"
  )

lazy val futures     = project.in(file("chapter-futures"))

lazy val remoting    = project.in(file("chapter-remoting"))

lazy val structure   = project.in(file("chapter-structure"))

