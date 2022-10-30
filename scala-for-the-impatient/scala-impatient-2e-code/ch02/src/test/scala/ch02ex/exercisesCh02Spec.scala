package ch02ex

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by edwardsj on 27/04/2017.
  */

trait MockOutput extends Output {
  var messages: Seq[String] = Seq()

  override def print(s: String) = {
    messages = messages :+ s
  }
}

class exercisesCh02Spec() extends FlatSpec with Matchers {

  val ex = new exercisesCh02 with MockOutput

  "signum" should "give -1, 0, 1 for negative, zero, positive numbers" in {
    ex.signum(-123445) shouldBe -1
    ex.signum(0) shouldBe 0
    ex.signum(23.55643634) shouldBe 1
  }

  "forLoopEquivalent" should "print 10 down to 0 on new lines" in {
    ex.forLoopEquivalent
    ex.messages shouldBe List("10", "9", "8", "7", "6", "5", "4", "3", "2", "1", "0")
  }

  "countDown" should "print all the numbers from the supplied integer down to zero" in {

    val ex2 = new exercisesCh02 with MockOutput

    ex2.countDown(42)
    ex2.messages shouldBe (0 to 42).map(x => x.toString).toList.reverse
  }

  "unicodeProduct" should "multiply the letters of Hello to make 9415087488" in {
    ex.unicodeProduct("Hello") shouldBe 9415087488L
  }

  "unicodeProductNoForLoop" should "multiply the letters of Hello to make 9415087488" in {
    ex.unicodeProductNoForLoop("Hello") shouldBe 9415087488L
  }

  "unicodeProductRecursive" should "multiply the letters of Hello to make 9415087488" in {
    ex.unicodeProductRecursive("Hello") shouldBe 9415087488L
  }

  "power" should "calculate 5^3 = 125" in {
    ex.power(5, 3) shouldBe 125
  }

  "power" should "calculate 16^0 = 1" in {
    ex.power(16, 0) shouldBe 1
  }


}
