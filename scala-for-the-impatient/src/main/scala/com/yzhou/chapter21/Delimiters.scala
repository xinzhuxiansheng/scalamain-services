package com.yzhou.chapter21

case class Delimiters(left: String, right: String)

object FrenchPunctuation {
  implicit val quoteDelimiters = Delimiters("《", "》")
}

object DelimitersMain extends App {

  /*
    import导包，需要注意代码顺序
   */
  import FrenchPunctuation.quoteDelimiters

  /*
    利用柯里化函数形式，来定义隐式参数
   */
  def quote(what: String)(implicit delims: Delimiters) =
    delims.left + what + delims.right

  /*
    quote()显示传入了Delimiters对象
   */
  //    println(quote("Bonjour le monde")(Delimiters("（", "）")))

  /*
    quote() 缺少Delimiters参数，而implicit val quoteDelimiters = Delimiters("《", "》") 统一定义了Delimiters

    在这种情况下，编译器将会查找一个类型为Delimiter的隐式值。这必须是一个被申明为implicit的值。编译器将会在如下两个地方
    查找这样的一个对象：
    * 在当前作用域所有可以用单个标识符值代的满足类型要求的val和def
    * 与所要求类型相关联的类型的伴生对象。相关联的类型包括所要求类型的本身，以及它的类型参数（如果它是一个参数化的类型的话）
   */
  println(quote("Bonjour le monde"))
}

