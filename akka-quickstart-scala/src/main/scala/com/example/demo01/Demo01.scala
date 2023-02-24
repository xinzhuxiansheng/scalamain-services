package com.example.demo01

object Demo01 extends App {

  def main(args: Array[String]): Unit = {
    val first = context.spawn(StartStopActor1(), "first")
    first ! "stop"
  }

}
