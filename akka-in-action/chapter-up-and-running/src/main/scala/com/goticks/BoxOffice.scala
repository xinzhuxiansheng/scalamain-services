package com.goticks

import scala.concurrent.Future

import akka.actor._
import akka.util.Timeout

object BoxOffice {
  val name = "boxOffice"

  def props(implicit timeout: Timeout) = Props(new BoxOffice())

  case class CreateEvent(name: String, tickets: Int)
  case class GetEvent(name: String)
  case object GetEvents
  case class GetTickets(event: String, tickets: Int)
  case class CancelEvent(name: String)

  case class Event(name: String, tickets: Int)
  case class Events(events: Vector[Event])

  sealed trait EventResponse
  case class EventCreated(event: Event) extends EventResponse
  case object EventExists extends EventResponse

}

class BoxOffice(implicit timeout: Timeout) extends Actor {
  import BoxOffice._
  import context._

  // 使用它的上下文创建TicketSeller,定义在独立的方法中，"使其在测试时易于覆盖"
  def createTicketSeller(name: String) =
    context.actorOf(TicketSeller.props(name), name)

  def receive = {
    case CreateEvent(name, tickets) =>
      def create() = {
        // 局部方法创建TicketSeller向其添加票券，并以EventCreated进行响应
        val eventTickets = createTicketSeller(name)
        val newTickets = (1 to tickets).map { ticketId =>
          TicketSeller.Ticket(ticketId)
        }.toVector
        eventTickets ! TicketSeller.Add(newTickets)
        sender() ! EventCreated(Event(name, tickets))
      }
      // 创建EventCreated并响应或以EventExists作为响应
      context.child(name).fold(create())(_ => sender() ! EventExists)


    /*
      在Akka中，forward方法用于传递消息。当一个actor A接收到一个消息，然后使用forward方法将消息发送给另一个actor B，
      消息会保留其原始的发送者信息。也就是说，如果actor B回复此消息，那么回复将直接返回给最初的发送者，而不是中间的actor A

      这种机制允许actor在处理消息时保持透明，就像他们只是消息的路由器，而不是消息的终点。这在需要进行消息路由或负载均衡的场景中非常有用
     */
    case GetTickets(event, tickets) =>
      def notFound() = sender() ! TicketSeller.Tickets(event)
      def buy(child: ActorRef) =
        child.forward(TicketSeller.Buy(tickets))

      context.child(event).fold(notFound())(buy)


    case GetEvent(event) =>
      def notFound() = sender() ! None
      def getEvent(child: ActorRef) = child forward TicketSeller.GetEvent
      context.child(event).fold(notFound())(getEvent)


    case GetEvents =>
      import akka.pattern.ask
      import akka.pattern.pipe

      def getEvents = context.children.map { child =>
        self.ask(GetEvent(child.path.name)).mapTo[Option[Event]]
      }
      def convertToEvents(f: Future[Iterable[Option[Event]]]) =
        f.map(_.flatten).map(l=> Events(l.toVector))

      pipe(convertToEvents(Future.sequence(getEvents))) to sender()


    case CancelEvent(event) =>
      def notFound() = sender() ! None
      def cancelEvent(child: ActorRef) = child forward TicketSeller.Cancel
      context.child(event).fold(notFound())(cancelEvent)
  }
}
