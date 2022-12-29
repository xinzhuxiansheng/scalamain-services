package com.yzhou.basic

object Test01 extends App {

  def test(code: => Unit): Unit = {
    println("start")
    code
    println("end")
  }

  //  test{
  //    println("when evaluated")
  //    println("bb")
  //  }

  val lst = List(20, 30, 60, 90)
  val rs = lst.foldLeft(0)((b, a) => {
    b + a
  })
  println(rs)
}
