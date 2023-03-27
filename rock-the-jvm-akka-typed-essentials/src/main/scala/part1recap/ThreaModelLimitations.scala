package part1recap

// 线程模式的局限性
object ThreaModelLimitations {

  class BankAccount(private var amount: Int) {

    override def toString = s"$amount"

    def withdraw(money: Int) = this.amount -= money

    def deposit(money: Int) = this.amount += money

    def getAmount = amount
  }

  val account = new BankAccount(2000)
  val depositThreads = (1 to 1000).map(_ => new Thread(() => account.deposit(1)))
  val withdrawThreads = (1 to 1000).map(_ => new Thread(() => account.withdraw(1)))

  /*
    - we don't know when the threads are finished
    - race conditions

    solution: synchronization
    other problems:
    - deadlocks
    - livelocks
   */

  // DR #2 - delegating a task to a thread
  var task: Runnable = null

  val runningThread: Thread = new Thread(() => {
    while (true) {
      while (task == null) {
        runningThread.synchronized {
          println("[background waiting for a task]")
          runningThread.wait()
        }
      }
      task.synchronized {
        println("[background] I have a task!")
        task.run()
        task = null
      }
    }
  })

  def delegateToBackgroundThread(r: Runnable) = {
    if (task == null) {
      task = r
      runningThread.synchronized {
        runningThread.notify()
      }
    }
  }

  def main(args: Array[String]): Unit = {
    //    (depositThreads ++ withdrawThreads).foreach(_.start())
    //    Thread.sleep(1000)
    //    println(account.getAmount)


    runningThread.start()
    Thread.sleep(1000)
    delegateToBackgroundThread(() => println("I'm running from another thread"))
    Thread.sleep(1000)
    delegateToBackgroundThread(() => println("This should run in the background again"))

  }
}
