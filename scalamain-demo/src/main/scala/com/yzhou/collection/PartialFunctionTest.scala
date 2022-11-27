package com.yzhou.collection

object PartialFunctionTest extends App {

  val list = List(1,2,3,"a",true);

  list.filter(x=>x.isInstanceOf[Int])
    .map(x => x.asInstanceOf[Int] * 10)
    .foreach(println)


}
