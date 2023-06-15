//package com.yzhou
//
//import scala.collection.mutable
//
///*
//  排行榜
// */
//case class Player(id: Int, scores: Int)
//
//class L1244 {
//  var hashMap = mutable.HashMap[Int, Player]()
//
//  implicit val customOrdering: Ordering[Player] = Ordering.by(person => (person.scores, person.id))
//  implicit val reverseOrdering: Ordering[Player] = customOrdering.reverse
//  var treeSet = mutable.TreeSet[Player]()(reverseOrdering)
//  /*
//    假如参赛者已经在排行榜上，就给他的当前得分增加 score 点分值并更新排行。
//    假如该参赛者不在排行榜上，就把他添加到榜单上，并且将分数设置为 score。
//   */
//  def addScore(playerId: Int, score: Int): Unit = {
//    // 改行代码只经历一次赋值
//    var player = hashMap.getOrElseUpdate(playerId,Player(playerId,score))
//
//  }
//
//  /*
//    返回前 K 名参赛者的 得分总和
//   */
//  def top(k: Int): Int = {
//    hashMap.toSeq.sortWith(_._2 > _._2).take(k).map(_._2).sum
//  }
//
//  /*
//    将指定参赛者的成绩清零。题目保证在调用此函数前，该参赛者已有成绩，并且在榜单上
//   */
//  def reset(playerId: Int): Unit = {
//
//  }
//
//}
