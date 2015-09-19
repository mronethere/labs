package game

object Utils {
  def printShape(shape: List[Point]) = {
    println("= = = =")
    val length = shape.map(point => Math.max(point.x, point.y)).max + 1
    val lines = (1 to length).map(x => (1 to length).map(x => ".").toArray).toArray
    shape.foreach(p => lines(p.x).update(p.y, "x"))
    lines.foreach(line => println(line.mkString(" ")))
  }
  lazy val shapes: List[Shape] = List(Blue(), Brown(), DarkBlue(), Green(), Grey(), LightBlue(), LightGreen(),
    Orange(), Pink(), Red(), Violet(), Yellow())

}
