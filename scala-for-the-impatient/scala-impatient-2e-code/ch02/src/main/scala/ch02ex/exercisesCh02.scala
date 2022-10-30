package ch02ex

/**
  * Created by edwardsj on 27/04/2017.
  */

trait Output {
  def print(s: String) = Console.println(s)
}

class exercisesCh02 extends Output {

  def signum(number: Double): Int = {
    if (number > 0)
      1
    else if (number == 0)
      0
    else
      -1
  }

  def forLoopEquivalent = {
    for (i <- 10 to 0 by -1) print(i.toString)
  }

  def countDown(n: Int) = {
    for (i <- n to 0 by -1) print(i.toString)
  }

  def unicodeProduct(s: String): Long = {
    var p: Long = 1
    for (c <- s) {
      p = p * c
    }
    p
  }

  def unicodeProductNoForLoop(s: String): Long = {
    s.toList.map(x => x.toLong).product
  }

  def unicodeProductRecursive(s: String): Long = {
    if (s.length == 0) 0
    else if (s.length == 1) s.head
    else s.head * unicodeProductRecursive(s.tail)
  }

  def power(x: Double, n: Double): Double = {
    if (n > 0 && n % 2 == 0) power(x, n / 2) * power(x, n / 2)
    else if (n > 0 && n % 2 != 0) x * power(x, n - 1)
    else if (n == 0) 1
    else 1 / power(x, -n)
  }

}

