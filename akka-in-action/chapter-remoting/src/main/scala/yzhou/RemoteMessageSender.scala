package yzhou

import akka.actor.{ActorSelection, ActorSystem}
import akka.pattern.ask
import akka.util.Timeout
import com.goticks.BoxOffice.GetEvent
import com.typesafe.config.ConfigFactory

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.duration.DurationInt
import scala.util.{Failure, Success}

object RemoteMessageSender {
  def main(args: Array[String]): Unit = {
    // 1. 创建 ActorSystem
    val config = ConfigFactory.load("yzhou")
    implicit val system: ActorSystem = ActorSystem("RemoteMessageSender", config)
    implicit val ec: ExecutionContext = system.dispatcher
    val selection: ActorSelection = system.actorSelection("akka://backend@0.0.0.0:2551/user/boxOffice")
    implicit val timeout: Timeout = Timeout(5.seconds)
    val future: Future[Any] = selection ? GetEvent("yzhou")
    future.onComplete {
      case Success(result) => {
        println(s"Received result: $result")
        system.terminate()
      }
      case Failure(exception) => {
        println(s"Failed with: $exception")
        system.terminate()
      }
    }
  }
}
