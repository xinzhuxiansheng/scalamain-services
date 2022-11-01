package com.rockthejvm.part2effects

import zio.*

import java.io.IOException
import java.net.NoRouteToHostException
import scala.util.{Failure, Success, Try}

object ZIOErrorHandling extends ZIOAppDefault {

  // ZIOs can fail
  val aFailedZIO = ZIO.fail("Something went wrong")
  val failedWithThrowable = ZIO.fail(new RuntimeException("Boom!"))
  val failedWithDescription = failedWithThrowable.mapError(_.getMessage)

  // attempt: run an effect that might throw an exception
  val badZIO = ZIO.succeed {
    println("Try something")
    val string: String = null
    string.length
  } // this is bad

  val anAttempt: ZIO[Any, Throwable, Int] = ZIO.attempt {
    println("Try something")
    val string: String = null
    string.length
  }

  // effectfully catch errors
  val catchError = anAttempt.catchAll(e => ZIO.succeed("Returning a differnet value bacause $e"))
  val catchSelectiveErrors = anAttempt.catchSome {
    case e: RuntimeException => ZIO.succeed(s"Ignoring runtime exceptions: $e")
    case _ => ZIO.succeed("Ignoring everything else")
  }
  // chain effects
  val aBetterAttempt = anAttempt.orElse(ZIO.succeed(56))
  // fold: handle both success and failure
  val handleBoth: ZIO[Any, Nothing, String] = anAttempt.fold(ex => s"Something bad happened: $ex", value => s"Length of the string")
  // effectful fold: foldZIO
  val handleBoth_v2 = anAttempt.foldZIO(
    ex => ZIO.succeed(s"Something bad happened: $ex"),
    value => ZIO.succeed(s"Length of the string was $value")
  )

  /*
    Conversions between Option/Try/Either to ZIO
  */
  val aTryTOZIO: ZIO[Any, Throwable, Int] = ZIO.fromTry(Try(42 / 0)) // can fail with Throwable

  // either -> ZIO
  val anEither: Either[Int, String] = Right("Success")
  val anEitherToZIO: ZIO[Any, Int, String] = ZIO.fromEither(anEither)
  // ZIO -> ZIO with Either as the value channel
  val eitherZIO = anAttempt.either
  // reverse
  val anAttempt_v2 = eitherZIO.absolve

  // option -> ZIO
  val anOption: ZIO[Any, Option[Nothing], Int] = ZIO.fromOption(Some(42))

  /**
   * Exercise: implement a version of fromTry, fromOption, fromEither, either, absolve
   * using fold and foldZIO
   */

  def try2ZIO[A](aTry: Try[A]): Task[A] = aTry match {
    case Failure(exception) => ZIO.fail(exception)
    case Success(value) => ZIO.succeed(value)
  }

  def either2ZIO[A, B](anEither: Either[A, B]): ZIO[Any, A, B] = anEither match {
    case Left(value) => ZIO.fail(value)
    case Right(value) => ZIO.succeed(value)
  }

  def option2ZIO[A](anOption: Option[A]): ZIO[Any, Option[Nothing], A] = anOption match {
    case Some(value) => ZIO.succeed(value)
    case None => ZIO.fail(None)
  }

  def zio2zioEither[R, A, B](zio: ZIO[R, A, B]): ZIO[R, Nothing, Either[A, B]] = zio.foldZIO(
    error => ZIO.succeed(Left(error)),
    value => ZIO.succeed(Right(value))
  )

  def absolveZIO[R, A, B](zio: ZIO[R, Nothing, Either[A, B]]): ZIO[R, A, B] = zio.flatMap {
    case Left(e) => ZIO.fail(e)
    case Right(v) => ZIO.succeed(v)
  }

  /*
    Errors = failures present in the ZIO type signature ("checked" errors)
    Defects = faulures that are unrecoverable, unforeseen, NOT present in the ZIO type signature

    ZIO[R,E,A] can finish with Exit[E,A]
      - Success[A] containing A
      - Cause[E]
        - Fail[E] containing the error
        - Die(t: Throwable) which was unforeseen

  */
  val divisionByZero: UIO[Int] = ZIO.succeed(1 / 0)

  val failedInt: ZIO[Any, String, Int] = ZIO.fail("I failed!")
  val failureCauseExposed: ZIO[Any, Cause[String], Int] = failedInt.sandbox
  val failureCauseHidden: ZIO[Any, String, Int] = failureCauseExposed.unsandbox
  // fold with cause
  val foldedWithCause = failedInt.foldCauseZIO(
    cause => ZIO.succeed(s"this failed with ${cause.defects}"),
    value => ZIO.succeed(s"this succeeded with $value")
  )

  /*
    Good practice:
    - at a lower level, your "error" should be treated
    - at a higher level, you should hide "error" and assume they are unrecoverable
  */
  def callHTTPEndpoint(url: String): ZIO[Any, IOException, String] =
    ZIO.fail(new IOException("no internet, dummy!"))

  val endpointCallWithDefects: ZIO[Any, Nothing, String] =
    callHTTPEndpoint("yzhou.com").orDie // all errors are now defects

  // refining the error channel
  def callHTTPEndpointWideError(url: String): ZIO[Any, Exception, String] =
    ZIO.fail(new IOException("No internet!!"))

  def callHTTPEndpoint_v2(url: String): ZIO[Any, IOException, String] =
    callHTTPEndpointWideError(url).refineOrDie[IOException] {
      case e: IOException => e
      case _: NoRouteToHostException => new IOException(s"No route to host to $url, can't fetch page")
    }

  // reverse: turn defects into the error channel
  val endpointCallWithError = endpointCallWithDefects.unrefine {
    case e => e.getMessage
  }

  /*
    Combine effects with different errors
  */
  trait AppError

  case class IndexError(message: String) extends AppError

  case class DbError(message: String) extends AppError

  val callApi: ZIO[Any, IndexError, String] = ZIO.succeed("page:<html></html>")
  val queryDb: ZIO[Any, DbError, Int] = ZIO.succeed(1)

  val combind = for {
    page <- callApi
    rowsAffected <- queryDb
  } yield (page, rowsAffected) // lost type safety

  /*
    Solutions:
      - design an error model
      - use Scala 3 union types
      - .mapError to some common error type
  */

  /*
    Exercises
  */

  // 1. - make the effect fail

  override def run = ???
}
