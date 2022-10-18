package com.yzhou.clist

import org.backuity.clist.Cli

object Test {

  def main(args: Array[String]) {
    Cli.parse(args).withCommand(new Cat) { case cat =>
      // the new Cat instance will be mutated to receive the command-line arguments
      println(cat.files)
    }
  }

}
