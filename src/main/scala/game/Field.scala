package game

import scala.annotation.tailrec


class Field(val size: Int, val matrix: List[List[Int]]) {
  def insert(point: Point): Field = {
    val before = matrix.take(point.x)
    val line = matrix(point.x)
    val newLine = line.take(point.y) ++ (point.token :: line.drop(point.y + 1))
    val after = matrix.drop(point.x + 1)
    new Field(0, before ++ (newLine :: after))
  }
  def insert(points: List[Point]): Field = {
    @tailrec def rez(points: List[Point], acc: Field): Field =
      if (points.isEmpty) acc
      else rez(points.tail, acc.insert(points.head))
    rez(points, this)
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
