package graphics

import java.lang.Math._

import api.{LabController, LabData}
import core.Boot.system.dispatcher

import scala.concurrent.Future

object Lab2Graphics extends LabController {
  def solve(data: LabData): Future[List[String]] = Future {
    val method = data.params.head
    val params = data.params.tail.take(4).map(_.toInt)
    buildPoints(params.head, params(1), params(2)) flatMap {
      case xy => List(xy._1.toString, xy._2.toString)
    }
  }

  def buildPoints(_x: Int, _y: Int, r: Int): List[(Int, Int)] = {
    var list = List.empty[(Int, Int)]
    list
  }

  def sign(x: Double) = if (x < 0) -1 else if (x > 0) 1 else 0
}
