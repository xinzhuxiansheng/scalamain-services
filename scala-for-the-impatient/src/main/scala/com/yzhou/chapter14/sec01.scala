package com.yzhou.chapter14

object sec01 extends App {
  var sign = 0
  val ch: Char = '+'

  ch match {
    case '+' => sign = 1
    case '-' => sign = -1
    case _ => sign = 0
  }

  println(sign)
}
