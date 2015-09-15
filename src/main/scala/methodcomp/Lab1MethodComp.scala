package methodcomp

import util.expression.FunctionExecutor

import scala.concurrent.Future
import scala.util.Try
import Math._

object Lab1MethodComp {
  def solve(data: InputData) = Future {
    val func = new FunctionExecutor(data.fun, Seq("x")).toFunctionDoubleToDouble
    val funcD = new FunctionExecutor(data.funD, Seq("x")).toFunctionDoubleToDouble
  }

  def halfDivision(e: Double, swap: Boolean, fun: Double => Double, funD: Double => Double, a: Double, b: Double) = Try {
    def iter(a: Double, b: Double, x: Double): Double = {
      if (abs(b - a) < e || fun(x) == 0) (a + b) / 2
      else {
        val (_a, _b) = if (fun(a) * fun(x) < 0) (a, x) else (x, b)
        iter(_a, _b, (a + b) / 2)
      }
      iter(a, b, (a + b) / 2)
    }
  }

  case class InputData(e: Double, swap: Boolean, fun: String, funD: String, a: Double, b: Double)
  case class OutputData(halfDivision: Double, chords: Double, newton: Double, iterating: Double)
}
