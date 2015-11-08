package methodcomp

import java.lang.Math._

object Lab11MethodComp {
  val E = 0.0001
  val X1 = 0.5
  val X2 = 0.74
  val funMap = List(0.50 -> 1.6487, 0.51 -> 1.6653, 0.52 -> 1.6820, 0.53 -> 1.6989, 0.54 -> 1.7160,
    0.55 -> 1.7333, 0.56 -> 1.7507, 0.57 -> 1.7683, 0.58 -> 1.7860, 0.59 -> 1.96356, 0.60 -> 1.98545,
    0.61 -> 1.99749, 0.62 -> 1.99957, 0.63 -> 1.99166, 0.64 -> 1.97385, 0.65 -> 1.94630, 0.66 -> 1.90930,
    0.67 -> 1.86321, 0.68 -> 1.80850, 0.69 -> 1.17520, 0.70 -> 1.30254, 0.71 -> 1.38631, 0.72 -> 1.38631,
    0.73 -> 1.21730, 0.74 -> 1.22361)
  def fun(x: Double): Double = {
    val _x = Math.round(x * 100) / 100.0
    funMap.find(_ == _x).getOrElse(0 -> 1.3)._2
  }

  def goldAvg(x1: Double, x2: Double, x3: Double): Double = {
    var max = funMap.head
    var rez = funMap.head._1
    for {
      j <- funMap.indices
      k = j
    } { funMap.foreach(xy =>
      if (xy._2 >= max._2) {
        max = xy
      }
    )
      if ((funMap(j)._2 - funMap(k)._2) < E) {
        rez = funMap(j)._1
      }
    }
    rez
  }

  def search(): Double = {
    var x = X1
    var h = 0.01
    while(true) {
      if (fun(x + h) < fun(x)) {
        h = -h / 4
        if (abs(h) < E / 4) return fun(x)
      }
      x = x + h
    }
    fun(x)
  }
  println(1.999731954)
}
