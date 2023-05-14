ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "akka-in-action"
  )

//lazy val channels    = project.in(file("chapter-channels"))
//
//lazy val cluster     = project.in(file("chapter-cluster"))
//
//lazy val conf        = project.in(file("chapter-conf-deploy"))
//
//lazy val up          = project.in(file("chapter-up-and-running"))

