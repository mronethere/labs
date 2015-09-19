package game

case class Point(x: Int, y: Int, token: Int)

object Point {
  def apply(xy:(Int, Int), token: Int) = new Point(xy._1, xy._2, token)
}