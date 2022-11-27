package com.yzhou.basic

object Test01 extends App {

  def test(code: => Unit): Unit ={
    println("start")
    code
    println("end")
  }

  test{
    println("when evaluated")
    println("bb")
  }
}
