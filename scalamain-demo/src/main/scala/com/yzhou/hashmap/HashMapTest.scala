package com.yzhou.hashmap

import scala.collection.mutable

object HashMapTest extends App {
  val hashMap = mutable.HashMap[String, Int]()

  def addValue(key: String, value: Int) = {
    hashMap(key) = hashMap.getOrElse(key, 0) + value
  }

  // 测试代码
  addValue("apple", 1)
  println(hashMap("apple"))  // 输出: 1

  addValue("apple", 2)
  println(hashMap("apple"))  // 输出: 3

  addValue("banana", 3)
  println(hashMap("banana"))  // 输出: 3
}
