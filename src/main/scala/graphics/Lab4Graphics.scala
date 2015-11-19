package graphics

import api.{LabController, LabData}
import core.Boot.system.dispatcher

import scala.concurrent.Future

object Lab4Graphics extends LabController {
  def solve(data: LabData): Future[List[String]] = Future {
    val params = data.params.map(_.toInt)
    buildPoints(params.head, params(1), params(2), params(3),
                params(4), params(5), params(6), params(7)) flatMap {
      case xy => List(xy._1.toString, xy._2.toString)
    }
  }


  def buildPoints(_x1: Int, _y1: Int, _x2: Int, _y2: Int,
                  xMin: Int, yMin: Int, xMax: Int, yMax: Int): List[(Int, Int)] = {
    def newOutCode(size: Int) = new Array[Boolean](size)
    def drawLine(x1: Int, y1: Int, x2: Int, y2: Int) = Lab1Graphics.buildPoints2(x1, y1, x2, y2)
    def buildOutCode(x: Int, y: Int) = {
      val codes = newOutCode(4)
      if (x > xMax) codes(2) = true
      else if (x < xMin) codes(3) = true
      if (y > yMax) codes(0) = true
      else if (y < yMin) codes(1) = true
      codes
    }
    def acceptCheck(outCode1: Array[Boolean], outCode2: Array[Boolean]) = {
      var accept = true
      for (i <- 0 to 3) {
        if (outCode1(i) || outCode2(i)) accept = false
      }
      accept
    }
    def rejectCheck(outCode1: Array[Boolean], outCode2: Array[Boolean]) = {
      var reject = false
      for (i <- 0 to 3) {
        if (outCode1(i) && outCode2(i)) reject = true
      }
      reject
    }
    var list = List.empty[(Int, Int)]
    var (accept, reject) = (false, false)
    var (arr1, arr2, arr3) = (newOutCode(4), newOutCode(4), newOutCode(4))
    var (x1, y1, x2, y2) = (_x1, _y1, _x2, _y2)
    list ++= drawLine(xMin,yMin,xMin,yMax); list ++= drawLine(xMax,yMin,xMax,yMax)
    list ++= drawLine(xMin,yMin,xMax,yMin); list ++= drawLine(xMin,yMax,xMax,yMax)

    var done = false
    while (!done) {
      arr1 = buildOutCode(x1, y1)
      arr2 = buildOutCode(x2, y2)
      reject = rejectCheck(arr1, arr2)
      accept = acceptCheck(arr1, arr2)
      if (reject) done = true
      else if (accept) done = true
      else {
        if (!(arr1(0) || arr1(1) || arr1(2) || arr1(3))) {
          x1 = x1 + x2; x2 = x1 - x2; x1 = x1 - x2
          y1 = y1 + y2; y2 = y1 - y2; y1 = y1 - y2
          for (i <- 0 to 3) {
            arr3(i) = arr1(i); arr1(i) = arr2(i); arr2(i) = arr3(i)
          }
        }
        if (arr1(0)) {
          x1 = x1 + ((x2 - x1) * (yMax - y1)) / (y2 - y1); y1 = yMax
        } else if (arr1(1)) {
          x1 = x1 + ((x2 - x1) * (yMin - y1)) / (y2 - y1); y1 = yMin
        } else if (arr1(2)) {
          y1 = y1 +( (y2 - y1) * (xMax - x1)) / (x2 - x1); x1 = xMax
        } else if (arr1(3)) {
          y1 = y1 + ((y2 - y1) * (xMin - x1)) / (x2 - x1); x1 = xMin
        }
      }
    }
    list ++ drawLine(x1, y1, x2, y2)
  }
}
