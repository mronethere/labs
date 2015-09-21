package game

import scala.annotation.tailrec


class Field(val size: Int, val matrix: List[List[Int]]) {
  def insert(point: Point): Option[Field] = {
    if (point.x >= matrix.size || point.y >= matrix.size || point.x < 0 || point.y < 0) None
    else {
      val (beforeLines, afterLines) = matrix.splitAt(point.x)
      val line = afterLines.head
      val (beforePoints, afterPoints) = line.splitAt(point.y)
      if (afterPoints.head != 0) None
      else {
        val newLine = beforePoints ++ (point.token :: afterPoints.tail)
        Some(Field(beforeLines ++ (newLine :: afterLines.tail)))
      }
    }
  }
  def insert(points: List[Point]): Option[Field] = {
    @tailrec def rez(points: List[Point], acc: Option[Field]): Option[Field] =
      if (points.isEmpty || acc.isEmpty) acc
      else rez(points.tail, acc.flatMap(_.insert(points.head)))
    rez(points, Some(this))
  }
}

object Field {
  def apply(size: Int) = new Field(size, empty(size))
  def apply(matrix: List[List[Int]]) = {
    val size = matrix.length
    require(matrix.forall(_.length == size), "not square matrix")
    new Field(size, matrix)
  }
  def empty(size: Int): List[List[Int]] = {
    val line = (1 to size map(x => 0)).toList
    (1 to size map(x => line)).toList
  }
}
