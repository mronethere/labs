package methodcomp

import api.{LabController, LabData}
import core.Boot.system.dispatcher


import scala.concurrent.{Future}

object Lab3MethodComp extends LabController {

  def solve(data: LabData): Future[List[String]] = {
    val n = data.params.head.toInt // n-matrix
    require(data.params.size == (n*n + n + 1), s"matrix isn't quadratic")
    GaussianMethod.solve(data.params.grouped(n + 1).toList.map(_.map(_.toDouble))).map(_.map(_.toString))
  }
}

object GaussianMethod {
  def solve(matrix: List[List[Double]]): Future[List[Double]] = {
    val size = matrix.size
    def iter(matrix: List[List[Double]], lineNum: Int): List[List[Double]] = {
      if (lineNum == size) matrix
      else iter(transform(matrix, lineNum), lineNum + 1)
    }
    Future(iter(matrix, 0).map(_.last))
  }

  def transform(matrix: List[List[Double]], lineNum: Int): List[List[Double]] = {
    val (taken, dropped) = matrix.splitAt(lineNum)
    val x = dropped.head(lineNum)
    val newLine = dropped.head.map(_ / x)
    subLines(taken, newLine, lineNum) ++ (newLine :: subLines(dropped.tail, newLine, lineNum))
  }

  def subLines(matrix: List[List[Double]], mainLine: List[Double], lineNum: Int) = matrix map { line =>
    val x = line(lineNum)
    line.zip(mainLine) map { case (a, b) => a - (b * x) }
  }
}



