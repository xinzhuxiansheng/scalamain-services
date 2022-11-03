package com.rockthejvm.part3concurrency

import zio._
import com.rockthejvm.utils._

object Resources extends ZIOAppDefault {

  // finalizers
  def unsafeMethod(): Int = throw new RuntimeException("Not an int here for you!")

  val anAttempt = ZIO.attempt(unsafeMethod())

  // finalizers
  val attemptWithFinalizer = anAttempt.ensuring(ZIO.succeed("finalizer").debugThread)
  // multiple finalizers
  val attemptWith2Finalizers = attemptWithFinalizer.ensuring(ZIO.succeed("another finalizer!").debugThread)
  // .onInterrupt, .onError, .onDone , .onExit

  // resource lifecycle
  class Connection(url: String) {
    def open() = ZIO.succeed(s"opening connection to $url...").debugThread

    def close() = ZIO.succeed(s"closing connection to $url...").debugThread
  }

  object Connection {
    def create(url: String) = ZIO.succeed(new Connection(url))
  }

  val fetchUrl = for {
    conn <- Connection.create("rockthejvm.com")
    fib <- (conn.open() *> ZIO.sleep(300.seconds)).fork
    _ <- ZIO.sleep(1.second) *> ZIO.succeed("interrupting").debugThread *> fib.interrupt
    _ <- fib.join
  } yield () // resource leak

  val cottectFetchUrl = for {
    conn <- Connection.create("rockthejvm.com")
    fib <- (conn.open() *> ZIO.sleep(300.seconds)).ensuring(conn.close()).fork
    _ <- ZIO.sleep(1.second) *> ZIO.succeed("interrupting").debugThread *> fib.interrupt
    _ <- fib.join
  } yield () // preventing leaks

  // tedious
  

  def run = cottectFetchUrl
}
