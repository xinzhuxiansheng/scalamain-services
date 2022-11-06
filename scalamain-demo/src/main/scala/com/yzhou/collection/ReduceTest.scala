package com.yzhou.collection

object ReduceTest extends App {

  val range01 = 1 to 10
  //  val t1 = Traversable(range01)
  //  println(s"t1: $t1")
  //  println(s"t1 size: ${t1.size}")
  //  range01.foreach(r => print(s"$r, "))
  //
  //  println("")
  //  println(" 1 to 10 use :_* ")

  val t2 = Traversable(range01: _*)
  println(s"t2: $t2")
  println(s"t2 size: ${t2.size}")
  println(t2.reduce((acc, x) => acc + x))
}
