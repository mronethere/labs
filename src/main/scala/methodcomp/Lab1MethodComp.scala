package methodcomp

import api.{LabController, LabData}

import scala.concurrent.Future
import scala.util.Try

object Lab1MethodComp extends LabController {
  def solve(data: LabData) = Future.successful(data.params)
}
