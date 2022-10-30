package com.yzhou.apply

case class Dog(val name:String){
  def bark():Unit = {
    println("dog is wangwang")
  }
}

object Dog{
  def main(args: Array[String]): Unit = {
    val daHuang = Dog("daHuang")
    daHuang.bark()
  }
}
