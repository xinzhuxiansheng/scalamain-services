package com.yzhou.http4s.reactservice

import cats.effect._
import com.comcast.ip4s._
import org.http4s.ember.server._
import org.http4s.implicits._
import org.http4s.server._

object ReactServer extends IOApp {
  import UsersService._

  override def run(args: List[String]): IO[ExitCode] = {
    val services = usersService
    val httpApp = Router( "/api" -> services).orNotFound

    EmberServerBuilder.default[IO]
      .withHost(ipv4"0.0.0.0")
      .withPort(port"5000")
      .withHttpApp(httpApp)
      .build
      .use(_ => IO.never) // 保持服务器运行
      .as(ExitCode.Success)
  }
}
