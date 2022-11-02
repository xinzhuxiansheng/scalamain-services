package com.yzhou.chapter21


/*
  型变
 */

class Person(name: String) {
  override def toString = getClass.getName + "" + name
}

class Student(name: String) extends Person(name)

class Pair[T](val first: T, val second: T) {
  override def toString = "(" + first + "," + second + ")"
}

object VarianceTest extends App {

  def makeFriends(p: Pair[Person]) =
    p.first + "and" + p.second + " are now friends"

  val fred = new Student("Fred")
  val wilma = new Student("Wilma")
  val studentPair = new Pair(fred, wilma)

  val fred2 = new Person("Fred")
  val wilma2 = new Person("Wilma")
  val personPair = new Pair(fred2, wilma2)

  // makeFriends(studentPair)
  // makeFriends(personPair)
}
