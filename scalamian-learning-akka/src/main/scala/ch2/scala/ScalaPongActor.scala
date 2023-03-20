package ch2.scala

import akka.actor.{Actor, Status}

class ScalaPongActor extends Actor {

  // 这里用到scala.PartialFunction 用来模式匹配
  override def receive: Receive = {
    case "Ping" => sender() ! "Pong" // 通过sender()方法获取了发送者的ActorRef。
    case _ =>
      sender() ! Status.Failure(new Exception("unknown message")) // Actor本身在任何情况下都不会自己返回Failure（即使Actor本身出现错误）。因此如果想要将发生的错误通知消息发送者，那么我们必须要主动发送一个Failure给对方。发送回Failure会导致请求方的Future被标记为失败
  }
}
