package methodcomp

import api.{LabController, LabData}


import scala.concurrent.Future

object Lab4MethodComp extends LabController {

  val E = 0.0001
  val A = 3
  val B = 4
  val M = 6.5
  def fun(x: Double) = Math.pow(Math.E, x) + Math.log(x) - 10 * x
  def funD(x: Double) = Math.pow(Math.E, x) + 1/ x - 10

  def halfDivision() = {
    def iter(a: Double = A, b: Double = B): Double = {
      val x = (a + b) / 2.0
      if (Math.abs(b - a) < E || fun(x) == 0) x
      else if (fun(a) * fun(b) < 0) iter(a, x)
      else iter(x, b)
    }
    iter()
  }

  def chords() = {
    def iter(a: Double = B, b: Double = A): Double = {
      val x = a - (fun(a) * (b - a)) / (fun(b) - fun(a))
      if (Math.abs(x - a) < E) x
      else iter(x, b)
    }
    iter()
  }

  def iterations() = {
    def iter(b: Double): Double = {
      val x = b - fun(b) / funD(b)
      if (Math.abs(x - b) < E) x
      else iter(x)
    }
    iter(A)
  }

  def rybakov(): Double = {
    val xs = new Array[Double](100)
    xs(0) = A
    var T = 0
    while (true) {
      xs(1) = xs(0) + Math.abs(xs(0)) / M
      if (xs(1) >= B) return xs(3)
      if (!(Math.abs(xs(1) - xs(0)) < E)) {
        if (T == 0)
          xs(3) = xs(1)
        xs(0) = xs(1) + E
        xs(2) = xs(1)
        T = 1
      } else {
        if (T == 1) {
          return (xs(3) + xs(2)) / 2
        }
        xs(0) = xs(1)
      }
    }
    0.0
  }






  def solve(data: LabData): Future[List[String]] = Future.successful(Nil)
}
