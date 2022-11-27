import scala.collection.mutable

/*

 */
object TwoSum01 extends App {
  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    def iter(i: Int, map: mutable.HashMap[Int, Int]): Array[Int] = {
      // 希望结果是个数组
      if (i == nums.size) throw new Exception("No result")
      else {
        map.get(target - nums(i)) match {
          case Some(j) => Array(i, j)
          case None => iter(i + 1, map + (nums(i) -> i))
        }
      }
    }

    iter(0, mutable.HashMap.empty)
  }

  var nums: Array[Int] = Array(2, 7, 11, 15)
  var target: Int = 9
  twoSum(nums, target).foreach(println)
}
