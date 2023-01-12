package com.yzhou.collection

object MkStringTest extends App {

  val a = Array("I", "love", "scala")

  // 字符串拼接形式
  println(a.mkString)

  // 以逗号分隔, 类似于java的 String.join()
  println(a.mkString(","))

  // 添加前缀和后缀
  println(a.mkString("[", ",", "]"))

  // 扩展
  println("扩展")
  val b = Array(Array("a", "b"), Array("c", "d"))
  println(b.flatten.mkString(","))
}
