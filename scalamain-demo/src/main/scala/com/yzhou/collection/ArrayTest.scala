package com.yzhou.collection

object ArrayTest {

  def main(args: Array[String]): Unit = {
    val a = Array(1, 2)
    val b = Array(3, 4)
    val c = a ++ b
    println(c mkString (""))
  }
}
