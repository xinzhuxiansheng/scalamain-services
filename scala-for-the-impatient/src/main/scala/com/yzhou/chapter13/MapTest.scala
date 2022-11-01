package com.yzhou.chapter13

object MapTest extends App {

  val immutableMap: Map[String,Int] = Map("Hello"-> 42)

  var map = Map("Hello" -> 42)
  map += ("Hello" -> 43)

  map.foreach(println(_))
}
