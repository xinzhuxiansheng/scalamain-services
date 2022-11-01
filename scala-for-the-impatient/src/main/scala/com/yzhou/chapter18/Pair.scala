package com.yzhou.chapter18

/*
  在类的定义中，可以用类型参数来定义变量、方法参数、以及返回值的类型
 */
class Pair[A, B](val first: A, val second: B) {
}


object Pair {
  val p = new Pair[Int, String](30, "yzhou")
}
