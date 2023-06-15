package com.yzhou.collection

import scala.collection.mutable

case class Person(name: String, age: Int)

object TreeSetTest {
  def main(args: Array[String]): Unit = {
    implicit val customOrdering: Ordering[Person] = Ordering.by(person => (person.age, person.name))
    implicit val reverseOrdering: Ordering[Person] = customOrdering.reverse

    val people = mutable.TreeSet(
      Person("Alice", 30),
      Person("Alice", 25),
      Person("Bob", 25),
      Person("Alice", 35),
      Person("Bob", 30)
    )(reverseOrdering) // 指定隐士的Ordering参数
    people.foreach(println)
  }
}
