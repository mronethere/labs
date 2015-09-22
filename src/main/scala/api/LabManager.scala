package api

import methodcomp._

import scala.concurrent.Future
import scala.concurrent.duration._
import core.Boot.system
import core.Boot.system._
import akka.pattern.after

object LabManager {

  def solveLab(data: LabData): Future[LabData] = after(5 second, system.scheduler) {
    labs(data.projectName, data.labId.toString).solve(data)
  } map {
    case list => LabData(data.projectName, data.labId, list, isSuccess = true)
  } recover {
    case e => LabData(data.projectName, data.labId, List(e.getMessage), isSuccess = false)
  }


  //TODO use macros for injection
  val labs: Map[(String, String), LabController] = Map(
    ("methodcomp", "1") -> Lab1MethodComp,
    ("methodcomp", "2") -> Lab2MethodComp
  )
}

trait LabController {
  def solve(data: LabData): Future[List[String]]
}
