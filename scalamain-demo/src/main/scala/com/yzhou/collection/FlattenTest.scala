package com.yzhou.collection

/*
  集合产品
  eg:  Traversable(Traversable(1,2,3),Traversable(2,3,4),Traversable(5,6,7,8))
  --> Traversable(1,2,3,2,3,4,5,6,7)
  平展方法很简单，无须传递什么参数。它会返回一个新的集合。在新的集合中，原集合中类型为Traversable的元素将被展开。

 */
object FlattenTest extends App {

  val s = Traversable(Traversable(1, 2, 3), Traversable(2, 3, 4), Traversable(5, 6, 7, 8))
  val s2 = s.flatten
  println(s2)
  println(s2.isInstanceOf[Traversable[Int]])

}
