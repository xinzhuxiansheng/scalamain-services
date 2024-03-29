package com.goticks

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.testkit.{DefaultTimeout, ImplicitSender, TestKit}
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpecLike

class BoxOfficeSpec extends TestKit(ActorSystem("testBoxOffice"))
    with AnyWordSpecLike
    with Matchers
    with ImplicitSender
    with DefaultTimeout
    with StopSystemAfterAll {
  "The BoxOffice" must {

    "Create an event and get tickets from the correct Ticket Seller" in {
      import BoxOffice._
      import TicketSeller._

      val boxOffice = system.actorOf(BoxOffice.props)
      val eventName = "RHCP"
      boxOffice ! CreateEvent(eventName, 10)
      expectMsg(EventCreated(Event(eventName, 10)))

      boxOffice ! GetTickets(eventName, 1)
      expectMsg(Tickets(eventName, Vector(Ticket(1))))

      boxOffice ! GetTickets("DavidBowie", 1)
      expectMsg(Tickets("DavidBowie"))
    }

    "Create a child actor when an event is created and sends it a Tickets message" in {
      import BoxOffice._
      import TicketSeller._

      val boxOffice = system.actorOf(Props(
          new BoxOffice  {
            override def createTicketSeller(name: String): ActorRef = testActor
          }
        )
      )

      val tickets = 3
      val eventName = "RHCP"
      val expectedTickets = (1 to tickets).map(Ticket).toVector
      boxOffice ! CreateEvent(eventName, tickets)
      expectMsg(Add(expectedTickets))
      expectMsg(EventCreated(Event(eventName, tickets)))
    }
  }
}
