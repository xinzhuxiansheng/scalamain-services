/*
  carry：10进制的10位数
  sum % 10 取余
  sum / 10 取十进制位的数
 */

object AddTwoNumbers02 extends App {
  def addTwoNumbers(l1: ListNode, l2: ListNode): ListNode = {
    def iter(l1: ListNode, l2: ListNode, carry: Int): ListNode = {
      (l1, l2) match {
        case (null, null) => if (carry > 0) new ListNode(carry) else null
        case (_, null) => {
          val sum = l1.x + carry
          new ListNode(sum % 10, iter(l1.next, null, sum / 10))
        }
        case (null, _) => {
          val sum = l2.x + carry
          new ListNode(sum % 10, iter(null, l2.next, sum / 10))
        }
        case (_, _) => {
          val sum = l1.x + l2.x + carry
          new ListNode(sum % 10, iter(l1.next, l2.next, sum / 10))
        }
      }
    }
    iter(l1, l2, 0)
  }

  def exec(): Unit = {
    var l1Node2 = ListNode(2, null)
    val l1Node4 = ListNode(4, null)
    val l1Node3 = ListNode(3, null)

    l1Node2.next = l1Node4
    l1Node4.next = l1Node3

    var l2Node5 = ListNode(5, null)
    val l2Node6 = ListNode(6, null)
    val l2Node4 = ListNode(4, null)

    l2Node5.next = l2Node6
    l2Node6.next = l2Node4

    var l3 = addTwoNumbers(l1Node2, l2Node5)
    var currentNode = l3
    while (currentNode.next != null) {
      print(currentNode.x)
      currentNode = currentNode.next
    }
    print(currentNode.x)
  }

  exec()
}

case class ListNode(_x: Int = 0, _next: ListNode = null) {
  var next: ListNode = _next
  var x: Int = _x
}
