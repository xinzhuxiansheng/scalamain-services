package com.yzhou.sum

import scala.collection.mutable.ArrayBuffer

object L15 {

  def main(args: Array[String]): Unit = {
    var arr = Array(0, 0, 0, 0)
    threeSum(arr)
  }

  def threeSum(nums: Array[Int]): List[List[Int]] = {
    var result = List[List[Int]]()
    var l = nums.size
    // 先排序
    var sortArr = nums.sorted
    // 遍历第一层
    for (x <- 0 to l - 1) {

      // -1, 0, 1, 2, -1, -4
      // 排序后 -4,-1, -1, 0, 1, 2
      // -1,-1 不能再执行
      if (x != 0 && sortArr(x) == sortArr(x - 1)) {
        // TODO跳过
      } else {
        var a = sortArr(x)
        var left = x + 1;
        var right = l - 1;

        while (left < right) {
          var sum = sortArr(x) + sortArr(left) + sortArr(right)
          if (sum == 0) {
            result = result :+ List(sortArr(x), sortArr(left), sortArr(right))
            // 这两个while主要应对 当数组为[0,0,0,0]时 会出现 [[0,0,0],[0,0,0]]
            while (left < right && sortArr(left) == sortArr(left + 1)) {
              left += 1
            }
            while (left < right && sortArr(right) == sortArr(right - 1)) {
              right -= 1
            }
          }
          if (sum > 0) {
            right -= 1
          } else {
            left += 1
          }
        }
      }
    }
    result.foreach(println(_))
    result
  }
}
