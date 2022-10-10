package com.rockthejvm.part1recap

object Variance {

  // OOP - subsititution
  class Animal

  class Dog(name: String) extends Animal

  // Variance question for List: if Dog <: Animal, then should List[Dog] <: List[Animal]

  // YES - CONVARIANT
  val lassie = new Dog("Lassie")
  val hachi = new Dog("Hachi")
  val laika = new Dog("Laika")

  val anAnimal: Animal = lassie
  val someAnimal: List[Animal] = List(lassie, hachi, laika)

  class MyList[+A] // MyList is COVARIANT in A

  val myAnimalList: MyList[Animal] = new MyList[Dog]

  // NO - then the type is INVARINAT
  trait Semigroup[A] {
    def combine(x: A, y: A): A
  }

  // all generics in Java
  // val aJavaList: java.util.ArrayList[Animal] = new util.ArrayList[Dog]()

  trait Vet[-A] {
    def heal(animal: A): Boolean
  }

  // Vet[Animal] is "better" than a Vet[Dog]
  val myVet: Vet[Dog] = new Vet[Animal] {
    override def heal(animal: Animal) = {
      println("Herre you go, you're good now...")
      true
    }
  }

  val healingLassie = myVet.heal(lassie)

  /*
    Rule of thumb:
    - if the type PRODUCES or RETRIEVES values of type A (e.g lists), then the type should be CONVARIANT
    - if the type CONSUMERS or ACTS ON values of type A (e.g a vet), then the type should be CONTRAVARIANT
   */

  /*
  class Cat extents Animal

  class Vet2[-A](val favouriteAnimal: A)

  val garfield = new Cat
  val theVet: Vet2[Animal] = new Vet2[Animal](garfield)
  val dogVet: Vet2[Dog] = theVet
  val favAnimal: Dog = dogVet.favouriteAnimal // must be a Dog - type conflict
  */

  // var fields are also in CONVARIANT position(same)

  /*
    class MutableContainer[+A](var contents:A)

    val containerAnimal: MutableContainer[Animal] = new MutableContainer[Dog](new Dog)
    containerAnimal.contents = new Cat // type conflict!
   */

  // types of method arguments are in CONTRAVARIANT position
  /*
    class MyList2[+A] {
      def add(element: A): MyList[A]
    }

    val animals: MyList2[Animal] = new MyList2[Cat]
    val biggerListOfAnimals: MyList2[Animal] = animals.add(new Dog) // type conflict
   */

  // solution: IDEN the type argument
  class MyList2[+A] {
    def add[B >: A](element: B): MyList[A] = ???
  }

  // method return types are in CONVARIANT position
  /*
    class Vet2[-A] {
      def rescueAnimal(): A
    }

    val vet: Vet2[Animal] = new Vet2[Animal]{
      def rescueAnimal(): Animal = new Cat
    }
    val lassieVet: Vet2[Dog] = vet
    val rescueDog:Dog = lassieVet.rescueAnimal() // must return a Dog, but it returns a Cat - type conflict
  */
  abstract class Vet2[-A] {
    def rescueAnimal[B <: A](): B
  }

  def main(args: Array[String]): Unit = {

  }

}
