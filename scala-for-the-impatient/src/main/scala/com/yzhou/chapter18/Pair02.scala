package com.yzhou.chapter18

/*
   下面代码包含异常， 我们并不知道first是否有compareTo方法，要解决这个问题，我们可以
   添加一个上界 <:Comparable[T]
  */
class Pair02[T <: Comparable[T]](val first: T, val second: T) {
  def smailler = if (first.compareTo(second) < 0) first else second
}

object Pair02 extends App {
  val p = new Pair02[Integer](2, 4)
  println(p.smailler)
}
