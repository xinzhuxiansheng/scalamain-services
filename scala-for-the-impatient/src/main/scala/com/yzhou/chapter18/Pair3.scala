package com.yzhou.chapter18

class Pair3[T](val first: T, val second: T) {
  def replaceFirst[R >: T](newFirst: R) = new Pair3(newFirst, second)

  override def toString = "(" + first + "," + second + ")"
}

class Person(name: String) {
  override def toString: String = getClass.getName + " " + name
}

class Student(name: String) extends Person(name)

object Pair3 {
  val fred = new Student("Fred")
  val wilma = new Student("Wilma")
  val p = new Pair3(fred,wilma)

  val barney = new Person("Barney")
}
