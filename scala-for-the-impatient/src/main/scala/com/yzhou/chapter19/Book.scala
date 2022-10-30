package com.yzhou.chapter19

class Book extends Document {
  def addChapter(chapter: String) = {
    println("addChapter")
    this
  }
}

object Book {
  def main(args: Array[String]): Unit = {
    var book: Book = new Book
    // 当后面添加 .addChapter(chapter1) 会报错
    // book.setTitle("scala books").addChapter(chapter1)
    // 解决方法是 将Document类的setTitle()的返回类型为this.type
    book.setTitle("scala books").addChapter("chapter19")

  }
}


