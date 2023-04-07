package com.yzhou

object L66 {

  def main(args: Array[String]): Unit = {
    var array = Array(9, 9, 9)
    plusOne(array).foreach(print(_))
    println()
  }

  def plusOne(digits: Array[Int]): Array[Int] = {
    var carry = 1
    for (i <- digits.length - 1 to 0 by -1) { // 从最右边开始遍历，直到0 ，并且每次递减 1
      digits(i) += carry
      carry = digits(i) / 10 // 给上一位求和
      digits(i) %= 10
    }

    // 因为 carry 对10 取余说明大于10 则需要
    if (carry != 0) {
      Array(carry) ++ digits
    } else {
      digits
    }
  }

}
