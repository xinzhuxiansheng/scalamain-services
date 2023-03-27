ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "scalamain-leetcode"
  )


// 编译Java源代码的设置
javacOptions ++= Seq("-source", "11", "-target", "11")

// 将Java源代码目录添加到编译路径中
unmanagedSourceDirectories in Compile += baseDirectory.value / "src" / "main" / "java"
