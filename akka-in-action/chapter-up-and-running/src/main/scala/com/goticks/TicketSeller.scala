package com.goticks

import akka.actor.{ Actor, Props, PoisonPill }

object TicketSeller {
  // 用于 ActorSystem 创建 TicketSeller
  def props(event: String) = Props(new TicketSeller(event))

  case class Add(tickets: Vector[Ticket])
  case class Buy(tickets: Int)
  case class Ticket(id: Int)
  case class Tickets(event: String,
                     entries: Vector[Ticket] = Vector.empty[Ticket])
  case object GetEvent
  case object Cancel

}

class TicketSeller(event: String) extends Actor {
  import TicketSeller._

  var tickets = Vector.empty[Ticket]

  def receive = {
    case Add(newTickets) => tickets = tickets ++ newTickets
    case Buy(nrOfTickets) =>
      val entries = tickets.take(nrOfTickets)
      if(entries.size >= nrOfTickets) {
        sender() ! Tickets(event, entries)
        tickets = tickets.drop(nrOfTickets)
      } else sender() ! Tickets(event)
    case GetEvent => sender() ! Some(BoxOffice.Event(event, tickets.size))
    case Cancel =>
      sender() ! Some(BoxOffice.Event(event, tickets.size))
      self ! PoisonPill
      /*
        在 Akka 中，PoisonPill 是一种特殊的消息，它会导致接收它的 actor 停止运行。
        当 actor 收到 PoisonPill 消息时，它会完成当前处理的消息，然后停止接收新的消息，并开始关闭过程。

      self 是 actor 自身的引用，你可以使用 self 来向 actor 自身发送消息。所以，self ! PoisonPill 的意思是
      actor 向自身发送了一个 PoisonPill 消息，这会导致 actor 在完成当前处理的消息后停止运行
       */
  }
}
