package com.yzhou.`trait`

trait A {
  def hello(): Unit = println("Hello from A!")
}

trait B {
  def hello(): Unit = println("Hello from B!")
}

//定义了两个特质 A 和 B，它们都包含了一个方法 hello。类 C 扩展了 A 和 B，但是它需要覆盖 hello 方法的实现，
//以实现它自己的逻辑。在 hello 方法中，通过 super[A].hello() 和 super[B].hello() 调用了 A 和 B 特质中的实现，
//同时添加了 C 的实现。
//
//总之，特质是 Scala 中非常强大和灵活的概念，它可以包含方法和属性的声明，也可以包含具体的实现。特质可以被混入到类中，
//提供了一种比多重继承更加灵活的代码复用机制。特质的多重混入机制可以让程序员更加方便地组合不同的特质，以实现复杂的功能

class C extends A with B {
  override def hello(): Unit = {
    super[A].hello()
    super[B].hello()
    println("Hello from C!")
  }
}