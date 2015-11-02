package methodcomp

import Math._

object Lab9MethodComp extends App {
  def F1(x: Double) = log(1 - 1.2 * sin(x)) * cos(3 * x)
  def F2(x: Double) = exp(x)
  val A = 0
  val B = PI
  val C = 3
  val D = 3.9
  val e = 0.001
  val N = 30

  def Q(a: Double, b: Double, f: Double => Double) = {
    val h = (b - a) / N ;
    var rez = 0d
    rez += f(a) / 2d
    rez += f(b) / 2d
    var i = 1
    while (i < N - 1) {
      val x = f(a + i * h)
      if (!x.isNaN) rez += f(a + i * h)
      i += 1
    }
    h * rez
  }
  val Q1 = Q(A, B, F1)
  val Q2 = Q(C, D, F2)
  val Z = sin(Q1 + Q2) / cos(Q1 - Q2) + 3 * Q2 - 4 * Q1
  println(Z)
}
