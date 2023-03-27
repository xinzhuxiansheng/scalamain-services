package com.yzhou.string

object Sliding {
  def main(args: Array[String]): Unit = {
    val str = "hello world"
    val windowSize = 3

    val windows = str.sliding(windowSize)

    windows.foreach(w => println(w))
  }
}
