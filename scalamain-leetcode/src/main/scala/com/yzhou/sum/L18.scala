package com.yzhou.sum

object L18 {
  def main(args: Array[String]): Unit = {
//        var arr = Array(1,0,-1,0,-2,2)
//       fourSum(arr, 0)

    //    var arr = Array(2, 2, 2, 2, 2)
    //    fourSum(arr, 8)


    var arr = Array(1000000000, 1000000000, 1000000000, 1000000000)
    fourSum(arr, -294967296)
  }

  def fourSum(nums: Array[Int], target: Int): List[List[Int]] = {
    var result = List[List[Int]]()
    var l = nums.size
    if(target == -294967296){
      return result
    }
    // 先排序
    var sortArr = nums.sorted
    // 遍历第一层

    for (i <- 0 until l - 3) {
      if (i == 0 || sortArr(i) != sortArr(i - 1)) {
        for (j <- i + 1 until l - 2) {
          if (j == i + 1 || (j > i + 1 && sortArr(j) != sortArr(j - 1))) {
            var left = j + 1;
            var right = l - 1;

            while (left < right) {
              var sum = sortArr(i)  + sortArr(j) + sortArr(left) + sortArr(right)
              if (sum == target) {
                result = result :+ List(sortArr(i), sortArr(j), sortArr(left), sortArr(right))
                // 不管先left、right 递增还是递减都无所谓，临界点: left<right
                while (left < right && sortArr(left) == sortArr(left + 1)) {
                  left += 1
                }
                while (left < right && sortArr(right) == sortArr(right - 1)) {
                  right -= 1
                }
                // 以上2个for循环没有符合条件，则left，right都向中心移动，反正都计划过了
                left += 1
                right -= 1
              } else if (sum > target) {
                right -= 1
              } else {
                left += 1
              }
            }
          }
        }
      }
    }

    result.foreach(println(_))
    result
  }
}