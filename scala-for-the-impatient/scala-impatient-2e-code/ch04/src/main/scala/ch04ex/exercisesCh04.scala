package ch04ex

import java.util.Scanner

import scala.collection.immutable.SortedMap
import scala.collection.JavaConversions.mapAsScalaMap
import java.util.Calendar._
import scala.collection.JavaConversions.propertiesAsScalaMap

/**
  * Created by edwardsj on 18/05/2017.
  */
class exercisesCh04 {

  def discount10(items: Map[String, Double]): Map[String, Double] = {
    for ((k, v) <- items) yield (k, v * 0.9)
  }

  def minmax(values: Array[Int]): (Int, Int) = {
    (values.min, values.max)
  }

  def lteqgt(values: Array[Int], v: Int): (Int, Int, Int) = {
    (values.filter(x => x < v).size, values.filter(x => x == v).size, values.filter(x => x > v).size)
  }

}

object myApp extends App {

  // Ex 4.2
  val in = new java.util.Scanner(new java.io.File("ch04/pandp.txt"))
  var words = scala.collection.mutable.Map[String, Int]()
  while (in.hasNext("(\\b[^\\s]+\\b*)")) {  // Not a perfect regex but regex is fucking boring
    val nextWord = in.next("(\\b[^\\s]+\\b*)")
    if (words.contains(nextWord))
      words(nextWord) += 1
    else
      words(nextWord) = 1
  }
  println(words)

  // Ex 4.3
  val in2 = new java.util.Scanner(new java.io.File("ch04/pandp.txt"))

  def nextWord(scanner: java.util.Scanner, wordCounts: Map[String, Int]): Map[String, Int] = {

    def addWord(word: String, wordCounts: Map[String, Int]): Map[String, Int] = {
      if (wordCounts.contains(word)) {
        val currCount = wordCounts(word)
        wordCounts.updated(word, currCount + 1)
      } else
        wordCounts + (word -> 1)
    }

    if (in2.hasNext("(\\b[^\\s]+\\b*)")) {
      val another = in2.next("(\\b[^\\s]+\\b*)")
      nextWord(scanner, addWord(another, wordCounts))
    } else {
      wordCounts
    }

  }

  println(nextWord(in2, Map()))

  // Ex 4.4
  val in3 = new java.util.Scanner(new java.io.File("ch04/pandp.txt"))

  def nextWord2(scanner: java.util.Scanner, wordCounts: SortedMap[String, Int]): SortedMap[String, Int] = {

    def addWord(word: String, wordCounts: SortedMap[String, Int]): SortedMap[String, Int] = {
      if (wordCounts.contains(word)) {
        val currCount = wordCounts(word)
        wordCounts.updated(word, currCount + 1)
      } else
        wordCounts + (word -> 1)
    }

    if (in3.hasNext("(\\b[^\\s]+\\b*)")) {
      val another = in3.next("(\\b[^\\s]+\\b*)")
      nextWord2(scanner, addWord(another, wordCounts))
    } else {
      wordCounts
    }

  }

  println(nextWord2(in3, SortedMap()))

  // Ex 4.5
  val in4 = new java.util.Scanner(new java.io.File("ch04/pandp.txt"))
  var words2: scala.collection.mutable.Map[String, Int] = new java.util.TreeMap[String, Int]
  while (in4.hasNext("(\\b[^\\s]+\\b*)")) {  // Not a perfect regex but regex is fucking boring
    val nextWord = in4.next("(\\b[^\\s]+\\b*)")
    if (words2.contains(nextWord))
      words2(nextWord) += 1
    else
      words2(nextWord) = 1
  }
  println(words2)

  // Ex 4.6
  val days = scala.collection.mutable.LinkedHashMap("Monday" -> MONDAY, "Tuesday" -> TUESDAY, "Wednesday" -> WEDNESDAY,
    "Thursday" -> THURSDAY, "Friday" -> FRIDAY, "Saturday" -> SATURDAY, "Sunday" -> SUNDAY)
  for ((k, v) <- days) println(k)

  // Ex 4.7
  val props: scala.collection.Map[String, String] = System.getProperties()
  val keyRowLength = props.keySet.map(x => x.length).max + 5
  for ((k, v) <- props) println(k + " " * (keyRowLength - k.length) + "| " + v)

  // Ex 4.10
  println("Hello".zip("World"))
  // Could e.g. use this to create a map from a string of lower-case letters to capitals
}