package ch05ex

/**
  * Created by Joseph on 06/07/2017.
  */
class exercisesCh05 {

  class Counter(private var value: Int = 0) {
    def increment() {
      if (value < Int.MaxValue)
        value += 1
      else
        value = 0
    }
    def current = value
  }

}
