package ch04ex

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by edwardsj on 18/05/2017.
  */
class exercisesCh04Spec extends FlatSpec with Matchers {

  val ex = new exercisesCh04
  val items = Map("iMac" -> 1200.00, "Rug" -> 250.00, "Saga" -> 15.00)

  "discount10" should "discount the values in a map by 10%" in {
    ex.discount10(items) shouldBe Map("iMac" -> 1080.00, "Rug" -> 225.00, "Saga" -> 13.50)
  }

  "minmax" should "return a pair of the smallest and largest ints in an array" in {
    ex.minmax(Array(34,2,67,4,87,1,39)) shouldBe (1,87)
  }

  "lteqgt" should "return a count of values lt, eq to and gt than supplied int" in {
    ex.lteqgt(Array(1,2,3,4,4,5,6,7,8), 4) shouldBe (3,2,4)
  }

}
