package api

import graphics._
import methodcomp._

import core.Boot.system
import core.Boot.system._

import scala.concurrent.Future
import scala.concurrent.duration._

import akka.pattern.after

object LabManager {

  def solveLab(data: LabData): Future[LabData] = {
    val timeout = after(5.second, using = system.scheduler)(Future.failed(new Error("Timeout exception")))
    val result = labs(data.projectName, data.labId.toString).solve(data).map {
      case list => LabData(data.projectName, data.labId, list, isSuccess = true)
    }
    Future firstCompletedOf Seq(timeout, result) recover {
      case e => LabData(data.projectName, data.labId, List(e.getMessage), isSuccess = false)
    }
  }


  //TODO use macros for injection
  val labs: Map[(String, String), LabController] = Map(
    ("methodcomp", "1") -> Lab1MethodComp,
    ("methodcomp", "2") -> Lab2MethodComp,
    ("graphics", "1") -> Lab1Graphics
  )
}

trait LabController {
  def solve(data: LabData): Future[List[String]]
}
