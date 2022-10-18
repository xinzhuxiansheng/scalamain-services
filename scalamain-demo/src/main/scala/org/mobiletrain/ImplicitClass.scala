package org.mobiletrain

object ImplicitClass {
  // 定义一个隐式类
  // 隐式类只能定义在类，trait，objec内部
  implicit class IntWithTimes(x: Int) {

    def times[A](f: => A): Unit = {
      // 递归方法需要指明其返回类型
      def loop(current: Int): Unit = {
        if (current > 0) {
          f
          loop(current - 1)
        }
      }
      loop(x)
    }
  }

  // 隐式类的构造函数只能带一个非隐式参数
  // implicit class Indexer[T](collection:Seq[T],index:Int) 不可以
  implicit class Indexer[T](collection:Seq[T])(implicit index:Int)
  // 在同一个作用域，不能有方法或者对象，成员与隐式类同名
}

