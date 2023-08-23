package com.yzhou.http4s

import cats.effect._
import cats.implicits._
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.implicits.http4sKleisliResponseSyntaxOptionT
import org.http4s.server.blaze._
import org.http4s.circe._
import io.circe.Json
import io.circe.parser._

object Http4sServer extends IOApp {

  val helloWorldService = HttpRoutes.of[IO] {
    case GET -> Root / "hello" / name =>
      Ok(s"Hello, $name!")

    case req@POST -> Root / "webhook" =>
      req.as[Json].flatMap { json =>
        IO(println(json.spaces4)) >> Ok("Received JSON")
      }
  }



  override def run(args: List[String]): IO[ExitCode] =
    BlazeServerBuilder[IO]
      .bindHttp(5000, "0.0.0.0")
      .withHttpApp(helloWorldService.orNotFound)
      .serve
      .compile
      .drain
      .as(ExitCode.Success)
}
