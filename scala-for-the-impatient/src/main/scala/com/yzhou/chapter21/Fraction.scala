package com.yzhou.chapter21

import scala.math.abs

class Fraction(n: Int, d: Int) {

  private val num: Int = if (d == 0) 1 else n * sign(d) / gcd(n, d)
  private val den: Int = if (d == 0) 0 else d * sign(d) / gcd(n, d)

  override def toString: String = num + "/" + den

  def sign(a: Int) = if (a > 0) 1 else if (a < 0) -1 else 0

  def gcd(a: Int, b: Int): Int = if (b == 0) abs(a) else gcd(b, a % b)

  def *(other: Fraction) = new Fraction(num * other.num, den * other.den)
}

object Fraction {
  def apply(n: Int, d: Int) = new Fraction(n, d)
}

object Main extends App {
  //  println(Fraction(4, 5))

  implicit def int2Fraction(n: Int) = Fraction(n, 1)

  /*
    1. 一般情况 *(乘号)只能用于 number，e.g ： 3 * 3
    2. 那当出现 3 * other type的时候，这足以说明，number是无法做到的，所以利用 implicit定义一个方法
    用来将 3 -> Franction(n,1) 。
    implicit def int2Fraction(n: Int) = Fraction(n, 1)
    3. 那为什么能将3装换成Fraction类型，这里考虑到一点，隐式缓缓的作用域
    int2Fraction(n: Int)方法的形参n是Int类型，当在Main class声明时，
    scala会将该类下所有Int类型 调用int2Fraction函数转成Fraction(n,1)类型,
    并且 Fraction类型也 定义了 *()，所以 3 * Fraction(4,5) 调用的是Fraction的*()

  */
  val result = 3 * Fraction(4, 5) // 3 => Calls int2Fraction(3)

  println(result)

  //  val a = 3 * 3
  //  println(a)
}