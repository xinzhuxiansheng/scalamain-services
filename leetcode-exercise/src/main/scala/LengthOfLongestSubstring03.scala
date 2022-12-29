
object LengthOfLongestSubstring03 extends App {
  def lengthOfLongestSubstring(s: String): Int = {
    Iterator.range(s.length - 1, -1, -1)
      .foldLeft((0, 0)) { case ((max, pre), i) =>
        var j = i + 1
        var n = 1
        while (j <= pre + i && s(j) != s(i)) {
          n += 1
          j += 1
        }
        (max.max(n), n)
      }._1
  }

  def lengthOfLongestSubstring02(s: String): Int = {
    Iterator.range(s.length - 1, -1, -1)
      .foldLeft((0, 0))((t1, t2) => {
        (t1, t2) match {
          case ((max, pre), i) =>
            var j = i + 1
            var n = 1
            while (j <= pre + i && s(j) != s(i)) {
              n += 1
              j += 1
            }
            (max.max(n), n)
        }
      })._1
  }

  def lengthOfLongestSubstring04(s: String): Int = {
    val s1 = s.toArray
    Iterator.range(s.length - 1, -1, -1)
      .foldLeft((0, 0))((u, i) => {
        var j = i + 1
        var n = 1
        while (j <= u._2 + i && s1(j) != s1(i)) {
          n += 1
          j += 1
        }
        (if (u._1 > n) u._1 else n, n)
      })._1
  }


  println(lengthOfLongestSubstring02("abcdabcddddd"))

  //println(Iterator.range("abcdefg".length - 1, -1, -1).getClass)

}
