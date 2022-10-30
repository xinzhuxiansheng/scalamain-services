package com.yzhou.chapter21

import java.io.File
import scala.io.Source
// import scala.language.implicitConversions

class RichFile(val from: File) {
  def read = Source.fromFile(from.getPath).mkString
}

object RichFile {

  def apply(from: File): RichFile = {
    new RichFile(from)
  }
}

object RichFileMain extends App {
  implicit def file2RichFile(from: File) = RichFile(from)

  /*
    java.io.File 没有read()方法，可以利用隐式转换将File转成RichFile，对File类增加read()
   */

  val contents = new File("/Users/yiche/Code/SCALA/scalamain-services/scala-for-the-impatient/src/main/scala/com/yzhou/chapter21/RichFile.scala").read
  println(contents)
}

object RichFileMain2 extends App{

}
