package com.yzhou.csv

import scala.io.Source

/**
 * 读取 csv文件
 */
object ReadCSVFile {

  case class College(id: Long, name: String)

  def main(args: Array[String]): Unit = {
    val bufferedSource = Source.fromResource("college_csv.csv")
    // val bufferedSource = Source.fromFile(s"${this.getClass.getResource(".").getPath}/college_csv.csv")
    val lines: List[College] = bufferedSource.getLines().filter(_.split(",").length != 1)
      .map(line => {
        val cols = line.split(",")
        College(cols(0).toLong, cols(1))
      }).toList
    bufferedSource.close();
    //println(lines)
    lines.foreach(println(_))
  }
}
