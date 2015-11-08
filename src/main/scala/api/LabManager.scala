package api

import graphics._
import methodcomp._

import core.Boot.system
import core.Boot.system._

import scala.concurrent.{TimeoutException, Future}
import scala.concurrent.duration._

import akka.pattern.after

object LabManager {

  def solveLab(data: LabData): Future[LabData] = {
    val timeout = after(45.second, using = system.scheduler)(Future.failed(new TimeoutException("Timeout exception")))
    val result = labs(data.projectName, data.labId.toString).solve(data).map {
      case list => LabData(data.projectName, data.labId, list, isSuccess = true)
    }
    Future firstCompletedOf Seq(timeout, result) recover {
      case e => LabData(data.projectName, data.labId, List(s"${e.getClass.toString}: ${e.getMessage}"), isSuccess = false)
    }
  }


  //TODO use macros for injection
  val labs: Map[(String, String), LabController] = Map(
    ("methodcomp", "1") -> Lab1MethodComp,
    ("methodcomp", "2") -> Lab2MethodComp,
    ("methodcomp", "3") -> Lab3MethodComp,
    ("graphics", "1") -> Lab1Graphics,
    ("graphics", "3") -> Lab3Graphics
  )
}

trait LabController {
  def solve(data: LabData): Future[List[String]]
}
