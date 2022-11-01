package com.yzhou.chapter13

object IterableTest extends App {

  val coll = Array(1,7,2,9)
  val iter = coll.iterator
  while(iter.hasNext)
    println(iter.next())

}
