package com.yzhou.apply

class ApplyTest(name: String) {
  def speak(): Unit = {
    println(s"$name is speaking")
  }

}

object ApplyTest extends App {
  def apply(name: String): ApplyTest = {
    new ApplyTest(name)
  }

  val p1 = ApplyTest("yzhou")
  p1.speak()
}
