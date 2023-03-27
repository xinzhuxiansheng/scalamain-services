package com.yzhou.array

object Sort {
  def main(args: Array[String]): Unit = {

    val arr = Array(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5)
    val sortedArr = arr.sorted
    println(sortedArr.mkString(", "))
    println("max : "+ sortedArr(arr.length-1)+" ,sencodMax:" + sortedArr(arr.length-2))
  }
}
