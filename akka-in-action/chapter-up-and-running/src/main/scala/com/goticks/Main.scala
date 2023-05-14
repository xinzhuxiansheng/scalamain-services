package com.goticks

import scala.concurrent.Future

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.stream.ActorMaterializer
import akka.util.Timeout

import com.typesafe.config.{ Config, ConfigFactory }

//继承scala的App（通用）
object Main extends App
    with RequestTimeout {

  // 通过com.typesafe.config 进行resources/application.conf加载
  val config = ConfigFactory.load() 
  val host = config.getString("http.host") // Gets the host and a port from the configuration
  val port = config.getInt("http.port")

  // ActorSystem 自创建以后就是活跃的，根据需要启动线程池
  implicit val system = ActorSystem()  // ActorMaterializer requires an implicit ActorSystem
  implicit val ec = system.dispatcher  // bindingFuture.map requires an implicit ExecutionContext

  // requestTimeout设置超时时间
  // RestApi提供HTTP路由
  val api = new RestApi(system, requestTimeout(config)).routes // the RestApi provides a Route

  val bindingFuture: Future[ServerBinding] =
    Http().newServerAt(host, port).bind(api) // starts the HTTP server
  
 
  val log =  Logging(system.eventStream, "go-ticks")
  bindingFuture.map { serverBinding =>
    log.info(s"RestApi bound to ${serverBinding.localAddress} ")
  }.recoverWith({
    case ex: Exception =>
      log.error(ex, "Failed to bind to {}:{}!", host, port)
      system.terminate()
  })
}

trait RequestTimeout {
  import scala.concurrent.duration._
  def requestTimeout(config: Config): Timeout = {
    val t = config.getString("akka.http.server.request-timeout")
    val d = Duration(t)
    FiniteDuration(d.length, d.unit)
  }
}
