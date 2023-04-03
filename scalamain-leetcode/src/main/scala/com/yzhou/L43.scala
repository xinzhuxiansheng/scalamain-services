package com.yzhou

object L43 {
  def main(args: Array[String]): Unit = {
    println(multiply("123", "123"))
  }


  def multiply(num1: String, num2: String): String = {

    val n1 = num1.length
    val n2 = num2.length
    val products = Array.fill(n1 + n2)(0)

    for {
      i <- (0 until n1).reverse
      j <- (0 until n2).reverse
    } {
      val digit1 = num1(i) - '0'
      val digit2 = num2(j) - '0'
      val pos1 = i + j
      val pos2 = i + j + 1
      val product = digit1 * digit2 + products(pos2)
      products(pos1) += product / 10 // 该位置是求和
      products(pos2) = product % 10
    }

    val sb = new StringBuilder
    for (digit <- products) {
      if (!(sb.length == 0 && digit == 0)) {
        sb.append(digit)
      }
    }
    if (sb.length == 0) "0" else sb.toString()
  }

}
