package com.yzhou.collection

object FlatMapTest extends App {

  val list1 = List(
    List(1, 2), List(3, 4)
  )

  val l1: List[Int] = list1.flatMap(
    list => {
      list
    }
  )

  println(l1)

}
