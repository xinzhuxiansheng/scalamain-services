package com.goticks

import akka.actor._
import akka.actor.ActorIdentity
import akka.actor.Identify

import scala.concurrent.duration._
import scala.language.postfixOps

class RemoteLookupProxy(path: String)
  extends Actor with Stash with ActorLogging {

  context.setReceiveTimeout(3 seconds)
  sendIdentifyRequest()

  def sendIdentifyRequest(): Unit = {
    val selection = context.actorSelection(path)
    selection ! Identify(path)
  }

  def receive = identify

  def identify: Receive = {
    case ActorIdentity(`path`, Some(actor)) =>
      context.setReceiveTimeout(Duration.Undefined)
      log.info("switching to active state")
      context.become(active(actor))
      context.watch(actor)
      unstashAll()

    case ActorIdentity(`path`, None) =>
      log.error(s"Remote actor with path $path is not available.")

    case ReceiveTimeout =>
      sendIdentifyRequest()

    case msg: Any =>
      log.warning(s"Stashing message $msg, remote actor is not ready yet.")
      stash()
  }

  def active(actor: ActorRef): Receive = {
    case Terminated(actorRef) =>
      log.info(s"Actor $actorRef terminated.")
      log.info("switching to identify state")
      context.become(identify)
      context.setReceiveTimeout(3 seconds)
      sendIdentifyRequest()

    case msg: Any => actor forward msg
  }
}
