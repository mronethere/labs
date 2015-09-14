package methodcomp

import scala.concurrent.Future

object Lab1MethodComp {
  def solve(data: InputData) = Future {

  }

  case class InputData(e: Double, swap: Boolean, fun: String, funD: String, a: Double, b: Double)
  case class OutputData(halfDivision: Double, chords: Double, newton: Double, iterating: Double)
}
