package com.yzhou.chapter18

object sec01 {

  /*

   */
  def getMiddle[T](a: Array[T]) = a(a.length / 2)

  val res: String = getMiddle[String](Array("Mary", "had", "a", "little", "lamb"))

  /*
    把 getMiddle函数内存地址赋值给 变量f
   */
  val f = getMiddle[String] _

  
}
