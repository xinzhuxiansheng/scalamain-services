package com.rockthejvm.part1recap

import scala.concurrent.duration.DurationInt

object ContextualAbstractionsScala2 {

  case class Person(name: String) {
    def greet(): String = s"Hi, my name is $name"
  }

  implicit class ImpersonableString(name: String) {
    def greet(): String =
      Person(name).greet()
  }

  val greeting = "Peter".greet() // new ImpersonableString("Peter").greet()

  // example: scala.concurrent.duration

  import scala.concurrent.duration

  val oneSecond = 1.second

  // implicit arguments and values
  def increment(x: Int)(implicit amount: Int) = x + amount

  implicit val defaultAmount: Int = 10
  val twelve = increment(2) // implicit argument 10 passed by the compiler

  def multiply(x: Int)(implicit factor: Int) = x * factor

  val aHundred = multiply(10) // same implicit argument passed by the compiler

  // more comlex example
  trait JSONSerializer[T] {
    def toJson(value: T): String
  }

  def convert2Json[T](value: T)(implicit serializer: JSONSerializer[T]): String =
    serializer.toJson(value)

  def main(args: Array[String]): Unit = {

  }

}
