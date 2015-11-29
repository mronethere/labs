package methodcomp

object Lab4MethodComp extends App {

  def laGrange(n: Int, x: Int, y: Int, xs: Array[Double]) = {
    var s = 0d
    for (i <- 0 until n) {
      val z = xs(i)
      var p1 = 1d
      var p2 = 1d
      for (k <- 0 until n) {
        if (i != k) {
          p1 = p1 * (xs(k) - xs(i))
          p2 = p2 * (z - xs(k))
        }
      }
      s = s + y * p1 / p2
    }
  }

  def newton(ys: Array[Double], n: Int) = {
    var c0 = ys(0)
    var k = n - 1
    var t = 0 ; var m = k
    do {
      t += 1
      for (i <- 1 to n) {
        ys(i) = ys(i + 1) - ys(i)
      }
    } while (t <= k)
    for (i <- 1 to k) {
      ys(i) = ys(i) / Math.tan(i) / n
    }
  }
}
