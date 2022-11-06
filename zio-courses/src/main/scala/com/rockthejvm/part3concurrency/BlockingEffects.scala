package com.rockthejvm.part3concurrency

import zio._
import com.rockthejvm.utils._

object BlockingEffects extends ZIOAppDefault {

  def blockingTask(n: Int): UIO[Unit] =
    ZIO.succeed(s"running blocking task $n").debugThread *>
      ZIO.succeed(Thread.sleep(3000)) *>
      blockingTask(n)

  // val program = ZIO.foreachPar(1 to 100).toList) (blockingTask)
  // thread stravtion

  // blocking thread pool
  val aBlockingZIO = ZIO.attemptBlocking {
    println(s"[${Thread.currentThread().getName}] running a long computation...")
    Thread.sleep(10000)
    42
  }

  // blocking code cannot (usually) be interrupted
  val tryInterrupting = for {
    blockingFib <- aBlockingZIO.fork
    _ <- ZIO.sleep(1.second) *> ZIO.succeed("interrupting...").debugThread *> blockingFib.interrupt
    mol <- blockingFib.join
  } yield mol

  // ca use attemptBlckingInterrupt
  // Thread.interrupt -> InterruptedException
  def aBlockingInterruptibleZIO = ZIO.attemptBlockingInterrupt {
    println(s"[${Thread.currentThread().getName}] running a long computation...")
    Thread.sleep(10000)
    42
  }

  // set a flag/switch
  // def interruptibleBlockingEffect(canceledFlag: AtomicBoolean): Task[Unit] =


  def run = tryInterrupting
}
