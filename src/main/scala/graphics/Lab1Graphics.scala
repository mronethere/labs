package graphics

import api.{LabController, LabData}
import core.Boot.system.dispatcher

import Math._
import scala.concurrent.Future

object Lab1Graphics extends LabController {
  def solve(data: LabData): Future[List[String]] = Future {
    val params = data.params.take(4).map(_.toInt)
    buildLines(params.head, params(1), params(2), params(3))
      .flatMap(xy => List(xy._1.toString, xy._2.toString))
  }

  def buildLines(x1: Int, y1: Int, x2: Int, y2: Int): List[(Int, Int)] = {
    val L = max(abs(y2 - y1), abs(x2 - x1))
    val (dx, dy) = ((x2 - x1) * 1.0 / L, (y2 - y1) * 1.0 / L)
    val (x, y) = (x1 + 0.5 * sign(dx), y1 + 0.5 * sign(dy))
    val addPoint = (list: List[(Double, Double)], any: Any) => {
      val (x, y) = list.head
      (x + dx, y + dy) :: list
    }
    (1 to L).foldLeft((x, y) :: Nil)(addPoint).map(xy => (floor(xy._1).toInt, floor(xy._2).toInt))
  }

  def sign(x: Double) = if (x < 0) -1 else if (x > 0) 1 else 0
}
