package com.rockthejvm.part2effects

import scala.concurrent.Future
import scala.io.StdIn

object Effects {

  // functional programming
  // EXPRESSIONS
  def combine(a: Int, b: Int): Int = a + b

  // local reasoning = type signature describes the kind of computation that will be performed
  // referential transparency
  val five = combine(2, 3)
  val five_v2 = 2 + 3
  val five_v3 = 5

  // not all expressions are RT
  val resultOfPrinting: Unit = println("Learning ZIO")
  val resultOfPrinting_v2: Unit = () // not the same

  // example2: changing a variable
  var anInt = 0
  val changingInt: Unit = (anInt = 42) // side effect
  val changingInt_v2: Unit = () // not the same program

  // side effects are inevitable
  /*
    Effect desires
    - the type signature describes what KIND of computation it will perform
    - the type signature describes the type of VALUE that it will produce
    - construction must be separate from the EXECUTION
   */

  /*
    Example: Options = possibly absent values
    - type signature describes the kind of computation = a possibly absent value
    - type signature says that the computation returns an A, if the computation does produce something
    - no side effects are needed

    => Option is an effect
   */
  val anOption: Option[Int] = Option(42)

  /*
    Example 2: Future
    - describes an asynchronous computation
    - produces a value of type A, if it finishes and it's successful
    - side effects are required, construction is NOT SEPARATE from execution
   */

  import scala.concurrent.ExecutionContext.Implicits.global

  val aFuture: Future[Int] = Future(42)

  /*
    Example 3: MyIO
    - describes a computation witch might perform side effects
    - produces a value of type A if the computation is successful
    - side effects are required, construction IS SEPARATE from execution
   */
  case class MyIO[A](unsafeRun: () => A) {
    def map[B](f: A => B): MyIO[B] =
      MyIO(() => f(unsafeRun()))

    def flatMap[B](f: A => MyIO[B]): MyIO[B] =
      MyIO(() => f(unsafeRun()).unsafeRun())
  }

  val anIOWithSideEffects: MyIO[Int] = MyIO(() => {
    println("producing effect")
    42
  })

  /**
   * Exercises - create some IO which
   * 1. measure the current time of the system
   * 2. meausre the duration of a computation
   *  - use exercise 1
   *  - use map/flatMap combinations of MyIO
   *    3. read something from the console
   *    4. print something to the console (e.g. "what's your name"), then read, then print a welcome message
   */

  // 1
  val currenTime: MyIO[Long] = MyIO(() => System.currentTimeMillis())

  // 2
  def measure[A](computation: MyIO[A]): MyIO[(Long, A)] = for {
    startTime <- currenTime
    result <- computation
    endTime <- currenTime
  } yield (endTime - startTime, result)

  def measure_v2[A](computation: MyIO[A]): MyIO[(Long, A)] = {
    //    val lambda1: Long => MyIO[(Long, A)] = startTime =>
    //      computation.flatMap { result =>
    //        currenTime.map { endTime =>
    //          (endTime - startTime, result)
    //        }
    //      }
    //    currenTime.flatMap(lambda1)
    MyIO { () =>
      val startTime = currenTime.unsafeRun()
      //      val lambda2: A => MyIO[(Long, A)] = result =>
      //        currenTime.map { endTime =>
      //          (endTime - startTime, result)
      //        }

      MyIO { () =>
        val result = computation.unsafeRun()

        currenTime.map { endTime =>
          (endTime - startTime, result)
        }.unsafeRun()


      }.unsafeRun()

    }
  }

  // 3
  val readLine: MyIO[String] = MyIO(() => StdIn.readLine())

  def putStrLn(line: String): MyIO[Unit] = MyIO(() => println(line))

  // 4
  val program = for {
    _ <- putStrLn("What's your name?")
    name <- readLine
    _ <- putStrLn(s"Welcome to Rock the JVM, $name!")
  } yield ()

  /**
   * A simplified ZIO
   */
  case class MyIO_v2[A](unsafeRun: () => A) {
    def map[B](f: A => B): MyIO[B] =
      MyIO(() => f(unsafeRun()))

    def flatMap[B](f: A => MyIO[B]): MyIO[B] =
      MyIO(() => f(unsafeRun()).unsafeRun())
  }


  def main(args: Array[String]): Unit = {
    //anIOWithSideEffects.unsafeRun()
    program.unsafeRun()
  }

}
