package com.yzhou.clist

import org.backuity.clist._

import java.io.File
// or if you do not like wildcard imports:
// import org.backuity.clist.{Command, opt, args}

class Cat extends Command(description = "concatenate files and print on the standard output") {

  // `opt`, `arg` and `args` are scala macros that will extract the name of the member
  // to use it as the option/arguments name.
  // Here for instance the member `showAll` will be turned into the option `--show-all`
  var showAll        = opt[Boolean](abbrev = "A", description = "equivalent to -vET")

  // an abbreviated form can be added, so that this option can be triggered both by `--number-nonblank` or `-b`
  var numberNonblank = opt[Boolean](abbrev = "b", description = "number nonempty output lines, overrides -n")

  // default values can be provided
  var maxLines       = opt[Int](default = 123)

  var files          = args[Seq[File]](description = "files to concat")
}
