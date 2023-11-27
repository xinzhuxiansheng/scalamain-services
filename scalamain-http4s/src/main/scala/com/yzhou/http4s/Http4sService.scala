//package com.yzhou.http4s
//
//import cats.effect._
//import cats.implicits._
//import org.http4s._
//import org.http4s.dsl.io._
//import org.http4s.implicits._
//import io.circe.Json
//import io.circe.generic.auto._
//import io.circe.syntax._
//import org.http4s.ember.server.EmberServerBuilder
//import org.http4s.circe
//
//object Http4sServer extends IOApp {
//  case class Order(id: Int, name: String)
//
//  val helloWorldService = HttpRoutes.of[IO] {
//    case GET -> Root / "hello" / name =>
//      Ok(s"Hello, $name!")
//
//    case req@POST -> Root / "webhook" =>
//      req.as[Json].flatMap { json =>
//        IO(println(json.spaces4)) >> Ok("Received JSON")
//      }
//
//    case POST -> Root / "flink" / "test" / "post" / "order" =>
//      // val orders = List(Order(1, "HUAWEI META60"), Order(2, "Apple 15 Pro"))
//      Ok(Order(1, "HUAWEI META60").asJson)
//  }
//
//
//
//  override def run(args: List[String]): IO[ExitCode] =
//    EmberServerBuilder.default[IO]
//      .bindHttp(5000, "0.0.0.0")
//      .withHttpApp(helloWorldService.orNotFound)
//      .serve
//      .compile
//      .drain
//      .as(ExitCode.Success)
//}
