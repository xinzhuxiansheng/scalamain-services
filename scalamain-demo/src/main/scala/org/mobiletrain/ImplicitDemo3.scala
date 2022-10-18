package org.mobiletrain

object ImplicitDemo3 extends App {

  // 一个特质，带有一个抽象方法
  trait Adder[T] {
    def add(x: T, y: T): T
  }

  //创建一个隐式对象
  implicit val a = new Adder[Int] {
    override def add(x: Int, y: Int): Int = x + y
  }

  def addTest(x: Int, y: Int)(implicit adder: Adder[Int]): Int = {
    adder.add(x, y)
  }

  println(addTest(1, 2))
}
