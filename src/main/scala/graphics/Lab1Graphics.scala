package graphics

import api.{LabController, LabData}
import core.Boot.system.dispatcher

import Math._
import scala.concurrent.Future

object Lab1Graphics extends LabController {
  def solve(data: LabData): Future[List[String]] = Future {
    val method = data.params.head
    val params = data.params.tail.take(4).map(_.toInt)
    (method match {
      case "1" => buildPoints1(params.head, params(1), params(2), params(3))
      case "2" => buildPoints2(params.head, params(1), params(2), params(3))
    }) flatMap {
      case xy => List(xy._1.toString, xy._2.toString)
    }
  }

  def buildPoints1(x1: Int, y1: Int, x2: Int, y2: Int): List[(Int, Int)] = {
    val L = max(abs(y2 - y1), abs(x2 - x1))
    val (dx, dy) = ((x2 - x1) * 1.0 / L, (y2 - y1) * 1.0 / L)
    val (x, y) = (x1 + 0.5 * sign(dx), y1 + 0.5 * sign(dy))
    def addPoint(list: List[(Double, Double)], any: Any) = {
      val (x, y) = list.head
      (x + dx, y + dy) :: list
    }
    (1 to L).foldLeft((x, y) :: Nil)(addPoint).map(xy => (floor(xy._1).toInt, floor(xy._2).toInt))
  }

  def sign(x: Double) = if (x < 0) -1 else if (x > 0) 1 else 0

  def buildPoints2(x1: Int, y1: Int, x2: Int, y2: Int): List[(Int, Int)] = {
    val (sx, sy) = (sign(x2 - x1), sign(y2 - y1))
    val (dx, dy) = (abs(x2 - x1), abs(y2 - y1))
    val swap = dy <= dx
    var e = if (swap) dy * 2 - dx else dx * 2 - dy
    val (de1, de2) = if (swap) (dy * 2, (dy - dx) * 2) else (dx * 2, (dx - dy) * 2)
    var list = (x1, y1) :: Nil
    var (x, y) = if (swap) (x1 + sx, y1) else (x1, y1 + sy)
    val max = if (swap) dx else dy
    for { i <- 1 to max } {
      if (e > 0) {
        e += de2
        if (swap) y += sy else x += sx
      } else e += de1
      list ::= (x, y)
      if (swap) x += sx else y += sy
    }
    list
  }

}
