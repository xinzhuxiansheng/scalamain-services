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
  println(Fraction(4, 5))

  implicit def int2Fraction(n: Int) = Fraction(n, 1)

  val result = 3 * Fraction(4, 5)
  println(result)

  val a = 3 * 3
  println(a)
}