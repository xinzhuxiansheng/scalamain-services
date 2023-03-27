package com.yzhou

object L904 {

  def main(args: Array[String]): Unit = {
    var data = Array(0, 1)
    totalFruit(data)
  }

  def totalFruit(fruits: Array[Int]): Int = {
    var left = 0
    var right = 0
    var basket = Map[Int, Int]()
    var maxFruits = 0

    while (right < fruits.length) {
      // 添加右边的水果到篮子里
      basket += fruits(right) -> (basket.getOrElse(fruits(right), 0) + 1)

      // 如果篮子里的水果种类超过2种，移动左指针
      while (basket.size > 2) {
        basket += fruits(left) -> (basket(fruits(left)) - 1)
        if (basket(fruits(left)) == 0) basket -= fruits(left)
        left += 1
      }

      // 更新最大水果数
      maxFruits = maxFruits.max(right - left + 1)

      right += 1
    }

    maxFruits
  }
}
