package lectures.part2fp

object PartialFunctions extends App {

  val aFunction = (x: Int) => x + 1 // Function1[Int,Int] === Int => Int

  val aFussyFunction = (x: Int) =>
    if (x == 1) 42
    else if (x == 2) 56
    else if (x == 5) 999
    else throw new FunctionNotApplicableException

  class FunctionNotApplicableException extends RuntimeException

  val aNiceFussyFunction = (x: Int) => x match {
    case 1 => 42
    case 2 => 56
    case 5 => 999
  }
  // {1,2,5} => Int

  val aPartialFunction: PartialFunction[Int, Int] = {
    case 1 => 42
    case 2 => 56
    case 5 => 999
  } // partial function value
  println(aPartialFunction(2))
  // println(aPartialFunction(57273))

  // PF utilities
  println(aPartialFunction.isDefinedAt(67))

  // lift
  val lifted = aPartialFunction.lift // Int => Option[Int]
  println(lifted(2))
  println(lifted(98))

  val pfChain = aPartialFunction.orElse[Int, Int] {
    case 45 => 67
  }
  println(pfChain(2))
  println(pfChain(45))

  // PF extend normal functions

  val aTotalFunction: Int => Int = {
    case 1 => 99
  }

  // HOFs accept partial functions as well
//  val aMappedList = List(1, 2, 3).map {
//    case 1 => 42
//    case 2 => 78
//    case 5 => 1000
//  }
//  println(aMappedList)

  /*
    Note: PF can only have ONE parameter type
   */

  /**
   * Exercies
   *
   * 1 - construct a PF instance yourself (anonymous class)
   * 2 -dumb chatbot as a PF
   */

  val aMuanualFussyFunction = new PartialFunction[Int, Int] {
    override def isDefinedAt(x: Int): Boolean =
      x == 1 || x == 2 || x == 5

    override def apply(x: Int): Int = x match {
      case 1 => 42
      case 2 => 65
      case 5 => 999
    }
  }
  val charbot: PartialFunction[String, String] = {
    case "hello" => "Hi,my name is HAL9000"
    case "goodbye" => "One you start talking to me, there is no return,human!"
    case "call mom" => "Unable to find your phone without your credit card"
  }
  scala.io.Source.stdin.getLines().map(charbot).foreach(println)
}
