package com.yzhou.tree

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object L112 {
  def main(args: Array[String]): Unit = {
    val arr: Array[Option[Int]] = Array(
      Some(5),
      Some(4),
      Some(8),
      Some(11),
      None,
      Some(13),
      Some(4),
      Some(7),
      Some(2),
      None,
      None,
      None,
      Some(1)
    )
    var root = buildTree(arr, 0)
    hasPathSum(root, 22).foreach(println(_))
  }

  def hasPathSum(root: TreeNode, targetSum: Int): List[List[Int]] = {
    val res = ListBuffer.empty[List[Int]]
    if (root == null) return res.toList
    val queue = mutable.Queue[(TreeNode, Int, List[Int])]((root, root.value, List(root.value)))

    while (queue.nonEmpty) {
      val (node, sum, path) = queue.dequeue()
      if (node.left == null && node.right == null && sum == targetSum) {
        res += path
      }
      if (node.left != null) queue.enqueue((node.left, sum + node.left.value, path :+ node.left.value))
      if (node.right != null) queue.enqueue((node.right, sum + node.right.value, path :+ node.right.value))
    }
    res.toList
  }

  /**
   * 构建一个完全二叉树
   * [5,4,8,11,null,13,4,7,2,null,null,null,1]
   *
   */
  def buildTree(nums: Array[Option[Int]], i: Int): TreeNode = {
    if (i > nums.length - 1 || nums(i) == None) {
      return null
    }
    var node = TreeNode(nums(i).get)
    node.left = buildTree(nums, 2 * i + 1);
    node.right = buildTree(nums, 2 * i + 2)
    node
  }
}

case class TreeNode(value: Int = 0, var left: TreeNode = null, var right: TreeNode = null)
