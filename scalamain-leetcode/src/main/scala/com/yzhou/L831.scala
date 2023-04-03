package com.yzhou

object L831 {
  def main(args: Array[String]): Unit = {
    maskPII("86-(10)12345678")
  }

  def maskPII(s: String): String = {
    if (s.contains("@")) { //邮箱
      val arr = s.split('@')
      val name1 = arr(0).toLowerCase()
      val name2 = arr(1).toLowerCase().split('.')
      s"${name1.head}${"*" * 5}${name1.last}@${name2.head}.${name2.last}"
    } else {
      // 如果是电话号码
      val nums = s.filter(_.isDigit) // 保留数据
      if (nums.length == 10) s"***-***-${nums.takeRight(4)}"
      else {
        val local = nums.takeRight(4)
        val intl = "*" * (nums.length - 10)
        s"+$intl-***-***-$local"
      }
    }
    ""
  }
}
