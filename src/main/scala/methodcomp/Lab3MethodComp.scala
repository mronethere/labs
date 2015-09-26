package methodcomp

import api.{LabController, LabData}
import core.Boot.system
import core.Boot.system.dispatcher
import spray.client.pipelining._
import spray.httpx.unmarshalling._
import spray.http._
import spray.util._

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.xml.NodeSeq

object Lab3MethodComp extends LabController {

  val pipeline: HttpRequest => Future[HttpResponse] = sendReceive

  implicit val nodeSeqUnmarshaller = Unmarshaller.forNonEmpty[NodeSeq]

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

object CramerMethod {
  import Lab3MethodComp._

  def findDeterminant(matrix: List[List[Double]]): Double = {
    val res = pipeline(Get(s"http://api.wolframalpha.com/v2/query?" +
      s"input=determinant+${toQueryParam(matrix)}&appid=UGX5QJ-2LH58RLT7X")) map {
      case response => response.entity.as[NodeSeq].get(0).child(3).child(1).child(1).text.toDouble
    }
    Await.result(res, 5.seconds)
  }


  def solve(matrix: List[List[Double]]): List[Double] = {
    ???
  }
  def toQueryParam(matrix: List[List[Double]]): String = {
    matrix.map(_.mkString("{", ",", "}")).mkString("{", ",", "}")
  }
}

