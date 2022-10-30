package com.yzhou.chapter19

class Document {
  def setTitle(title: String):this.type = {
    println("setTitle")
    this
  }

  def setAuthor(author: String) = {
    println("setAuthor")
    this
  }
}

object Document{

  def main(args: Array[String]): Unit = {
    var document:Document = new Document
    document.setTitle("scala books").setAuthor("yzhou")
  }
}
