package com.yzhou.`trait`

/*
  定义一个特质Greeting，它包含一个抽象方法greet和一个具体的实现
 */
trait Greeting {
  def greet(): Unit = println("Hello, world!")
}

/*
  定义一个MyGreeting，它扩展了特质Greeting，并覆盖了greet方法的实现
 */
class MyGreeting extends Greeting {
  override def greet(): Unit = println("Hello, Scala!")
}