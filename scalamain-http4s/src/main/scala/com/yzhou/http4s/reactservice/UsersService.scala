package com.yzhou.http4s.reactservice

import cats.effect._
import io.circe.Json
import io.circe.syntax._
import org.http4s._
import org.http4s.dsl.io._
import io.circe.generic.auto._
import org.http4s.circe._

object UsersService {
  private case class Order(id: Int, name: String)

  val usersService: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root / "users" / "login" =>
      Ok(Result(0, "OK", "OK").asJson)

    case req@POST -> Root / "users" / "login" =>
      req.as[Json].flatMap { json =>
        IO(println(json.spaces4)) >> Ok(Result(0, "OK", "OK").asJson)
      }

    case POST -> Root / "flink" / "test" / "post" / "order" =>
      // val orders = List(Order(1, "HUAWEI META60"), Order(2, "Apple 15 Pro"))
      Ok(Order(1, "HUAWEI META60").asJson)
  }
}
