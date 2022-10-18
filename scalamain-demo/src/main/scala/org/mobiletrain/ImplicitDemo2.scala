package org.mobiletrain

import scala.language.postfixOps

class ImplicitDemo2 extends App {
  var a: Int = 10
  var b: Double = 100.99

  b = 100
  b = a

  // 定义一个隐式转换函数，把double转换成int
  implicit def doubleToInt(x: Double): Int = x toInt

  // 可以编译通过
  a = b
}
