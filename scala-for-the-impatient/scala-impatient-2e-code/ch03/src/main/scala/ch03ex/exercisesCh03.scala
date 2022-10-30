package ch03ex

import Math._
import scala.collection.mutable.{ Buffer, ArrayBuffer }
import scala.collection.JavaConversions.asScalaBuffer
import java.util.TimeZone
import java.awt.datatransfer._

/**
  * Created by edwardsj on 02/05/2017.
  */
class exercisesCh03 {

  def randomArray(n: Int): Array[Int] = {
    for (_ <- 0 until n) yield floor(n * Math.random).toInt
  }.toArray

  def adjacentSwap(arr: Array[Int]): Array[Int] = {

    def swapFirstTwo(arr: Array[Int]): Array[Int] = {
      if (arr.size > 1)
        Array(arr(1), arr(0)) ++ arr.drop(2)
      else
        arr
    }

    if (arr.size > 1)
      swapFirstTwo(arr).take(2) ++ adjacentSwap(arr.drop(2))
    else
      arr
  }

  def positiveFirst(arr: Array[Int]): Array[Int] = {
    arr.filter(x => x > 0) ++ arr.filter(x => x <= 0)
  }

  def average(arr: Array[Double]): Double = {
    arr.sum / arr.size
  }

  def revSort(arr: Array[Int]): Array[Int] = {
    arr.sorted.reverse
  }

  def revSort(arrBuff: ArrayBuffer[Int]): ArrayBuffer[Int] = {
    arrBuff.sorted.reverse
  }

  def deDupe(arr: Array[Int]): Array[Int] = {
    arr.distinct
  }

  def removeAllButFirstNegative(arr: ArrayBuffer[Int]): ArrayBuffer[Int] = {
    val negativeElements = for (i <- 0 until arr.size if arr(i)  < 0) yield i
    val elementsToRemove = negativeElements.reverse.dropRight(1)
    for (r <- elementsToRemove) arr.remove(r)
    arr
  }

}

object TestApp extends App {

  println("Ex 3.9")
  val americaList = for (z <- TimeZone.getAvailableIDs if z.startsWith("America")) yield println(z.stripPrefix("America/"))
  americaList.sorted

  println("Ex 3.10")
  val flavors = SystemFlavorMap.getDefaultFlavorMap.asInstanceOf[SystemFlavorMap]
  val natives: Buffer[String] = flavors.getNativesForFlavor(DataFlavor.imageFlavor)
  println(natives)
}
