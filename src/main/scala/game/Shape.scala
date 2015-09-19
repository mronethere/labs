package game

trait Shape {
  def opts: List[List[Point]]
  def points(opt: Int): List[Point]
}

/** 2
 *       x
 *   x x x x
 */
case class Yellow() extends Shape {
  val opts = List (
    List((0, 0), (1, 0), (1, 1), (2, 0), (3, 0)),
    List((0, 1), (1, 0), (1, 1), (2, 1), (3, 1)),
    List((0, 0), (1, 0), (2, 0), (2, 1), (3, 0)),
    List((0, 1), (1, 1), (2, 0), (2, 1), (3, 1)),
    List((0, 2), (1, 0), (1, 1), (1, 2), (1, 3)),
    List((0, 1), (1, 0), (1, 1), (1, 2), (1, 3)),
    List((0, 0), (0, 1), (0, 2), (0, 3), (1, 2)),
    List((0, 0), (0, 1), (0, 2), (0, 3), (1, 1))
  ).map(list => list.map(Point(_, 2)))

  def points(opt: Int) = opts(opt)
}

/** 3
 *   x x
 *     x x
 *       x
 */
case class Violet() extends Shape {
  val opts = List (
    List((0, 0), (0, 1), (1, 1), (1, 2), (2, 2)),
    List((0, 1), (0, 2), (1, 0), (1, 1), (2, 0)),
    List((0, 2), (1, 1), (1, 2), (2, 0), (2, 1)),
    List((0, 0), (1, 0), (1, 1), (2, 1), (2, 2))
  ).map(list => list.map(Point(_, 3)))

  def points(opt: Int) = opts(opt)
}

/** 4
 *   x
 *   x x x
 *   x
 */
case class Green() extends Shape {
  val opts = List (
    List((0, 0), (1, 0), (1, 1), (1, 2), (2, 0)),
    List((0, 2), (1, 0), (1, 1), (1, 2), (2, 2)),
    List((0, 0), (0, 1), (0, 2), (1, 1), (2, 1)),
    List((0, 1), (1, 1), (2, 0), (2, 1), (2, 2))
  ).map(list => list.map(Point(_, 4)))

  def points(opt: Int) = opts(opt)
}

/** 5
 *   x   x
 *   x x x
 */
case class LightGreen() extends Shape {
  val opts = List (
    List((0, 0), (0, 2), (1, 0), (1, 1), (1, 2)),
    List((0, 0), (0, 1), (0, 2), (1, 0), (1, 2)),
    List((0, 0), (0, 1), (1, 0), (2, 0), (2, 1)),
    List((0, 0), (0, 1), (1, 1), (2, 0), (2, 1))
  ).map(list => list.map(Point(_, 5)))

  def points(opt: Int) = opts(opt)
}

/** 6
 *   x x
 *   x x x
 */
case class Grey() extends Shape {
  val opts = List (
    List((0, 0), (0, 1), (1, 0), (1, 1), (2, 0)),
    List((0, 0), (0, 1), (1, 0), (1, 1), (2, 1)),
    List((0, 0), (1, 0), (1, 1), (2, 0), (2, 1)),
    List((0, 1), (1, 0), (1, 1), (2, 0), (2, 1)),
    List((0, 0), (0, 1), (1, 0), (1, 1), (1, 2)),
    List((0, 1), (0, 2), (1, 0), (1, 1), (1, 2)),
    List((0, 0), (0, 1), (0, 2), (1, 0), (1, 1)),
    List((0, 0), (0, 1), (0, 2), (1, 1), (1, 2))
  ).map(list => list.map(Point(_, 6)))

  def points(opt: Int) = opts(opt)
}

/** 7
 *   x
 *   x
 *   x x x
 */
case class Blue() extends Shape {
  val opts = List (
    List((0, 0), (0, 1), (0, 2), (1, 0), (2, 0)),
    List((0, 0), (0, 1), (0, 2), (1, 2), (2, 2)),
    List((0, 0), (1, 0), (2, 0), (2, 1), (2, 2)),
    List((0, 0), (1, 0), (2, 0), (2, 1), (2, 2))
  ).map(list => list.map(Point(_, 3)))

  def points(opt: Int) = opts(opt)
}

/** 8
 *   x
 *   x x x
 *       x
 */
case class DarkBlue() extends Shape {
  val opts = List (
    List((0, 0), (1, 0), (1, 1), (1, 2), (2, 2)),
    List((0, 2), (1, 0), (1, 1), (1, 2), (2, 0)),
    List((0, 0), (0, 1), (1, 1), (2, 1), (2, 2)),
    List((0, 1), (0, 2), (1, 1), (2, 0), (2, 1))
  ).map(list => list.map(Point(_, 8)))

  def points(opt: Int) = opts(opt)
}

/** 9
 *   x
 *   x x
 */
case class LightBlue() extends Shape {
  val opts = List (
    List((0, 0), (0, 1), (1, 0)),
    List((0, 0), (0, 1), (1, 1)),
    List((0, 0), (1, 0), (1, 1)),
    List((0, 1), (1, 0), (1, 1))
  ).map(list => list.map(Point(_, 9)))

  def points(opt: Int) = opts(opt)
}

/** 10
 *     x
 *   x x x
 *   x
 */
case class Orange() extends Shape {
  val opts = List (
    List((0, 1), (1, 0), (1, 1), (1, 2), (2, 0)),
    List((0, 1), (1, 0), (1, 1), (1, 2), (2, 2)),
    List((0, 0), (1, 0), (1, 1), (1, 2), (2, 1)),
    List((0, 2), (1, 0), (1, 1), (1, 2), (2, 1)),
    List((0, 1), (1, 0), (1, 1), (2, 1), (2, 2)),
    List((0, 1), (0, 2), (1, 0), (1, 1), (2, 1)),
    List((0, 1), (1, 1), (1, 2), (2, 0), (2, 1)),
    List((0, 0), (0, 1), (1, 1), (1, 2), (2, 1))
  ).map(list => list.map(Point(_, 10)))

  def points(opt: Int) = opts(opt)
}

/** 11
 *   x x x
 *       x x
 */
case class Pink() extends Shape {
  val opts = List (
    List((0, 0), (0, 1), (0, 2), (1, 2), (1, 3)),
    List((0, 1), (0, 2), (0, 3), (1, 0), (1, 1)),
    List((0, 1), (1, 1), (2, 0), (2, 1), (3, 0)),
    List((0, 0), (1, 0), (2, 0), (2, 1), (3, 1)),
    List((0, 2), (0, 3), (1, 0), (1, 1), (1, 2)),
    List((0, 0), (0, 1), (1, 1), (1, 2), (1, 3)),
    List((0, 0), (1, 0), (2, 0), (2, 1), (3, 1)),
    List((0, 1), (1, 1), (2, 0), (2, 1), (3, 0))
  ).map(list => list.map(Point(_, 11)))

  def points(opt: Int) = opts(opt)
}

/** 12
 *     x x
 *   x x
 */
case class Brown() extends Shape {
  val opts = List (
    List((0, 1), (0, 2), (1, 0), (1, 1)),
    List((0, 0), (0, 1), (1, 1), (1, 2)),
    List((0, 1), (1, 0), (1, 1), (2, 0)),
    List((0, 0), (1, 0), (1, 1), (2, 1))
  ).map(list => list.map(Point(_, 12)))

  def points(opt: Int) = opts(opt)
}

/** 13
 *         x
 *   x x x x
 */
case class Red() extends Shape {
  val opts = List (
    List((0, 0), (1, 0), (1, 1), (1, 2), (1, 3)),
    List((0, 3), (1, 0), (1, 1), (1, 2), (1, 3)),
    List((0, 0), (0, 1), (0, 2), (0, 3), (1, 0)),
    List((0, 0), (0, 1), (0, 2), (0, 3), (1, 3)),
    List((0, 0), (0, 1), (1, 0), (2, 0), (3, 0)),
    List((0, 0), (0, 1), (1, 1), (2, 1), (3, 1)),
    List((0, 0), (1, 0), (2, 0), (3, 0), (3, 1)),
    List((0, 1), (1, 1), (2, 1), (3, 0), (3, 1))
  ).map(list => list.map(Point(_, 13)))

  def points(opt: Int) = opts(opt)
}



