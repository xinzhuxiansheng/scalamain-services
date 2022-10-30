package ch03ex

import org.scalatest.{FlatSpec, Matchers}
import scala.collection.mutable.ArrayBuffer

/**
  * Created by edwardsj on 02/05/2017.
  */
class exercisesCh03Spec extends FlatSpec with Matchers {

  val ex = new exercisesCh03

  "randomArray" should "make a random array of length n" in {

    val a = ex.randomArray(6)

    a.length shouldBe 6
    for (x <- a) {
      x should be < 6
      x should be >= 0
    }

  }

  "adjacentSwap" should "swap adjacent integers in an array" in {
    ex.adjacentSwap(Array(1,2,3,4,5)) shouldBe Array(2,1,4,3,5)
  }

  "positiveFirst" should "return postive values then 0/negative in original order" in {
    ex.positiveFirst(Array(-4, 2, 0, -7, 1, -5)) shouldBe Array(2, 1, -4, 0, -7, -5)
  }

  "average" should "calculate the average of a list of doubles" in {
    ex.average(Array(1, 2.5, 5.75, 8.95)) shouldBe 4.55
  }

  "revSort" should "sort ints in reverse numerical order" in {
    ex.revSort(Array(4,42,76,24,62)) shouldBe Array(76,62,42,24,4)
  }

  "revSort" should "sort ints in reverse numerical order in an array buffer" in {
    ex.revSort(ArrayBuffer(4,42,76,24,62)) shouldBe ArrayBuffer(76,62,42,24,4)
  }

  "deDupe" should "de-duplicate an array" in {
    ex.deDupe(Array(1,1,2,3,1,2,5,3,6,2)) shouldBe Array(1,2,3,5,6)
  }

  "removeAllButFirstNegative" should "remove all negatives except the first" in {
    ex.removeAllButFirstNegative(ArrayBuffer(5,-74,3,7,-9,21,7,93,7,-8985,621,-6)) shouldBe ArrayBuffer(5,-74,3,7,21,7,93,7,621)
  }

}
