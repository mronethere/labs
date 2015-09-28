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

  def solve(data: LabData): Future[List[String]] = Future {
    val n = data.params.head.toInt // n-matrix
    require(data.params.size == (n*n + n + 1), s"matrix isn't quadratic")
    val matrix = data.params.tail.grouped(n + 1).toList.map(_.map(_.toDouble))
    val quadratic = matrix.map(_.init)
    val vector = matrix.map(_.last)
    val gaussian = GaussianMethod.solve(matrix).map(_.toString)
    val cramer = CramerMethod.solve(quadratic, vector)
    val inverse = InverseMatrixMethod.solve(quadratic, vector).map(_.toString)
    ("gaussian" :: gaussian) ++ ("cramer"  :: cramer._2) ++ ("inverse" :: inverse)
  }

  def iter(matrix: List[List[Double]], lineNum: Int, size: Int): List[List[Double]] = {
    if (lineNum == size) matrix
    else iter(GaussianMethod.transform(matrix, lineNum), lineNum + 1, size)
  }
}

object GaussianMethod {
  def solve(matrix: List[List[Double]]): List[Double] = {
    val size = matrix.size
    Lab3MethodComp.iter(matrix, 0, size).map(_.last)
  }

  def transform(matrix: List[List[Double]], lineNum: Int): List[List[Double]] = {
    val (taken, dropped) = matrix.splitAt(lineNum)
    val x = dropped.head(lineNum)
    val newLine = dropped.head.map(_ / x)
    subLines(taken, newLine, lineNum) ++ (newLine :: subLines(dropped.drop(1), newLine, lineNum))
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

  def solve(quadratic: List[List[Double]], vector: List[Double]): (Boolean, List[String]) = {
    val mainDet = findDeterminant(quadratic)
    val transposed = quadratic.transpose
    val dets = (for {
      i <- transposed.indices
      (before, after) = transposed.splitAt(i)
    } yield before ++ (vector :: after.drop(1))) map findDeterminant
    if (mainDet != 0) {
      (true, dets.map(x => (x / mainDet).toString).toList)
    } else (false, List(if(dets.exists(_ != 0)) "no" else "any"))
  }

  def toQueryParam(matrix: List[List[Double]]): String = {
    matrix.map(_.mkString("{", ",", "}")).mkString("{", ",", "}")
  }
}

object InverseMatrixMethod {

  def solve(quadratic: List[List[Double]], vector: List[Double]) = {
    val size = vector.size
    val identic = identical(size)
    val concat = quadratic zip identic map (lines => lines._1 ++ lines._2)
    val transformed = Lab3MethodComp.iter(concat, 0, size)
    val inversed = transformed.map(_.drop(size))
    multiply(inversed, vector)
  }

  def multiply(matrix: List[List[Double]], vector: List[Double]) = {
    matrix.map(line => (line zip vector).map(ab => ab._1 * ab._2).sum)
  }

  // rewrite this sometime
  def identical(size: Int): List[List[Double]] = {
    val matrix = new Array[Double](size).map(x => new Array[Double](size))
    var i = 0
    while (i < size) {
      matrix(i).update(i, 1.0)
      i += 1
    }
    matrix.map(_.toList).toList
  }
}
