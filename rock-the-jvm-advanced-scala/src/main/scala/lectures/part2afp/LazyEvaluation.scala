package lectures.part2afp

import lectures.part2afp.LazyEvaluation.{Cons, EmptyStream, MyStream}

import scala.annotation.tailrec

object LazyEvaluation extends App {

  // lazy DELAYS the evaluation of values
  lazy val x: Int = {
    println("hello")
    42
  }
  println(x)
  println(x)

  // examples of implications
  // side effects
  def sideEffectCondition: Boolean = {
    println("Boo")
    true
  }

  def simpleCondition: Boolean = false

  lazy val lazyCondition = sideEffectCondition
  println(if (simpleCondition && lazyCondition) "yes" else "no")

  // in conjunction with call by name
  def byNameMethod(n: => Int): Int = {
    lazy val t = n // only evaluated once
    t + t + t + 1
  }

  def retrieveMagicValue = {
    // side effect or a long computation
    println("waiting")
    Thread.sleep(1000)
    42
  }

  println(byNameMethod(retrieveMagicValue))
  // use lazy vals

  // filtering with lazy vals
  def lessThan30(i: Int): Boolean = {
    println(s"$i is less than 30?")
    i < 30
  }

  def greaterThan20(i: Int): Boolean = {
    println(s"$i is greater than 20?")
    i > 20
  }

  val numbers = List(1, 25, 40, 5, 23)
  val lt30 = numbers.filter(lessThan30) // List(1,25,40,5,23)
  val gt20 = lt30.filter(greaterThan20)
  println(gt20)

  val lt30lazy = numbers.withFilter(lessThan30) // lazy vals under the hood
  val gt20lazy = lt30lazy.withFilter(greaterThan20)
  println
  gt20lazy.foreach(println)

  // for-comprehensions use withFilter with guards
  for {
    a <- List(1, 2, 3) if a % 2 == 0
  } yield a + 1
  List(1, 2, 3).withFilter(_ % 2 == 0).map(_ + 1) // List[Int]

  /*
    Exercise: implement a lazily evaluated, singly linked STREAM of elements

    naturals = MyStream.from(1)(x => x+1 ) = stream of natural numbers (potentially infinite)
    naturals.take(100).foreach(println) // lazily evaluated stream of the first 100 naturals (finite stream)
    naturals.foreach(println) // will crash - infinite
    naturals.map(_ * 2) // stream of all even numbers (potentially infinite)
   */

  abstract class MyStream[+A] {
    def isEmpty: Boolean

    def head: A

    def tail: MyStream[A]

    def #::[B >: A](element: B): MyStream[B] // prepend operator

    def ++[B >: A](anotherStream: => MyStream[B]): MyStream[B] // concatenate two streams

    def foreach(f: A => Unit): Unit

    def map[B](f: A => B): MyStream[B]

    def flatMap[B](f: A => MyStream[B]): MyStream[B]

    def filter(predicate: A => Boolean): MyStream[A]

    def take(n: Int): MyStream[A] // takes the first n elements out of this stream

    def takeAsList(n: Int): List[A] = take(n).toList()

    /*
      [1 2 3].toList([])=

     */
    @tailrec
    final def toList[B >: A](acc: List[B] = Nil): List[B] =
      if (isEmpty) acc
      else tail.toList(head :: acc)
  }

  object EmptyStream extends MyStream[Nothing] {
    def isEmpty: Boolean = true

    def head: Nothing = throw new NoSuchElementException

    def tail: MyStream[Nothing] = throw new NoSuchElementException

    def #::[B >: Nothing](element: B): MyStream[B] = new Cons(element, this)

    def ++[B >: Nothing](anotherStream: => MyStream[B]): MyStream[B] = anotherStream

    def foreach(f: Nothing => Unit): Unit = ()

    def map[B](f: Nothing => B): MyStream[B] = this

    def flatMap[B](f: Nothing => MyStream[B]): MyStream[B] = this

    def filter(predicate: Nothing => Boolean): MyStream[Nothing] = this

    def take(n: Int): MyStream[Nothing] = this
  }

  class Cons[+A](hd: A, tl: => MyStream[A]) extends MyStream[A] {
    def isEmpty: Boolean = false

    override val head: A = hd

    override lazy val tail: MyStream[A] = tl // call by need

    /*
      val s = new Cons(1,EmptyStream)
      val prepended = 1 #:: s = new Cons(1,s)
     */
    def #::[B >: A](element: B): MyStream[B] = new Cons(element, this)

    def ++[B >: A](anotherStream: => MyStream[B]): MyStream[B] = new Cons(head, tail ++ anotherStream)

    def foreach(f: A => Unit): Unit = {
      f(head)
      tail.foreach(f)
    }

    /*
      s = new Cons(1,?)
      mapped = s.map(_+1) = new Cons(2,s.tail.map(_+1))
        ... mapped.tail
     */
    def map[B](f: A => B): MyStream[B] = new Cons(f(head), tail.map(f)) // preserve lazy evaluation

    def flatMap[B](f: A => MyStream[B]): MyStream[B] = f(head) ++ tail.flatMap(f)

    def filter(predicate: A => Boolean): MyStream[A] =
      if (predicate(head)) new Cons(head, tail.filter(predicate))
      else tail.filter(predicate) // preserves lazy eval!

    def take(n: Int): MyStream[A] = {
      if (n <= 0) EmptyStream
      else if (n == 1) new Cons(head, EmptyStream)
      else new Cons(head, tail.take(n - 1))
    }
  }
}

object MyStream {
  def from[A](start: A)(generator: A => A): MyStream[A] =
    new Cons(start, MyStream.from(generator(start))(generator))
}


object StreamsPlayground extends App {
  val naturals = MyStream.from(1)(_ + 1)
  println(naturals.head)
  println(naturals.tail.head)
  println(naturals.tail.tail.head)

  val startFrom0 = 0 #:: naturals // naturals.#::(0)
  println(startFrom0.head)

  startFrom0.take(10000).foreach(println)

  // map, flatMap
  println(startFrom0.map(_ * 2).take(100).toList())
  println(startFrom0.flatMap(x => new Cons(x, new Cons(x + 1, EmptyStream))).take(10).toList())
}