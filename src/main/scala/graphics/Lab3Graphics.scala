package graphics

import java.lang.Math._

import api.{LabController, LabData}
import core.Boot.system.dispatcher

import scala.concurrent.Future

object Lab3Graphics extends LabController {
  def solve(data: LabData): Future[List[String]] = Future {
    val params = data.params.map(_.toInt)
    buildPoints(params.head, params(1), params(2)) flatMap {
      case xy => List(xy._1.toString, xy._2.toString)
    }
  }

  def buildPoints(cx: Int, cy: Int, radius: Int): List[(Int, Int)] = {
    var list = List.empty[(Int, Int)]
    var (x, y) = (0, radius)
    var (dt, b) = (2 * (1 - radius), 0)
    while (y >= 0) {
      list ++= List((cx + x, cy + y), (cx + x, cy - y),
        (cx - x, cy + y), (cx - x, cy - y))
      if (dt < 0 && b <= 0) {
        x += 1
        dt += 2 * x + 1
      } else if (dt > 0 && b >= 0) {
        y -= 1
        dt -= 2 * y + 1
      } else {
        x += 1
        dt += 2 * (x - y)
        y -= 1
      }
    }
    list
  }

}
