package game

object Utils {
  lazy val shapes: List[Shape] = List(Blue(), Brown(), DarkBlue(), Green(), Grey(), LightBlue(), LightGreen(),
    Orange(), Pink(), Red(), Violet(), Yellow())

  def printShape(shape: List[Point]) = {
    println("= = = =")
    val length = shape.map(point => Math.max(point.x, point.y)).max + 1
    val lines = (1 to length).map(x => (1 to length).map(x => ".").toArray)
    shape.foreach(p => lines(p.x).update(p.y, tokenize(p.token)))
    lines.foreach(line => println(line.mkString(" ")))
  }

  def printMatrix(field: Field) = {
    val matrix = field.matrix
    val points = for {
      x <- matrix.indices
      y <- matrix.indices
    } yield Point(x, y, matrix(x)(y))
    printShape(points.toList)
  }

  val tokens =  {
    val chars = ('a' to 'z').filter(_ != 'o').toList
    (0 until (chars.size + 1)) zip ('o' :: chars)
  }
  def tokenize(x: Int) = if (x >= tokens.size) "." else tokens(x)._2.toString

  def moveRight(points: List[Point]): List[Point] = points.map(p => Point(p.x, p.y + 1, p.token))
  def moveDown(points: List[Point]): List[Point] = points.map(p => Point(p.x + 1, p.y, p.token))

  /*def solve(field: Field, shapes: List[Shape]):Set[List[List[Point]]] = {
    if (field.isFull) Set(List(List()))
    else for {
      solutions <- solve()
    }
  }*/

}
