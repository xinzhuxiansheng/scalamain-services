package com.goticks

import scala.concurrent.Future
import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer

trait Startup extends RequestTimeout {
  def startup(api: Route)(implicit system: ActorSystem) = {
    val host = system.settings.config.getString("http.host") // Gets the host and a port from the configuration
    val port = system.settings.config.getInt("http.port")
    startHttpServer(api, host, port)
  }

  def startHttpServer(api: Route, host: String, port: Int)
                     (implicit system: ActorSystem) = {
    implicit val ec = system.dispatcher //bindAndHandle requires an implicit ExecutionContext
    val bindingFuture: Future[ServerBinding] =
      Http().newServerAt(host, port).bind(api) //Starts the HTTP server

    val log = Logging(system.eventStream, "go-ticks")
    bindingFuture.map { serverBinding =>
      log.info(s"RestApi bound to ${serverBinding.localAddress} ")
    }.recover {
      case ex: Exception =>
        log.error(ex, "Failed to bind to {}:{}!", host, port)
        system.terminate()
    }
  }
}
