package util.expression

import scala.util.Try


class FunctionExecutor(func: String, args: Seq[String]) {

  private val engine = new ScalaScriptEngine().engine

  /**
   * Substitutes `args` by `values` in `func` and executes it.
   *
   * @param values values
   * @return result of evaluating
   */
  def evalUnsafe(values: Seq[String]): Any = {
    require(values.size == args.size, "wrong values length")
    (args zip values) foreach {
      case (arg, value) => engine.put(arg, value)
    }
    engine.eval {
      s"""
        |import Math._
        |
        |$func
      """.stripMargin
    }
  }

  /**
   * Wraps `evalUnsafe` within Try
   *
   * @see evalUnsafe
   */
  def eval(values: Seq[String]): Try[Any] = Try(evalUnsafe(values))

  /**
   * Converts function executor to valid scala function
   *
   * @return Double => Double
   */
  def toFunctionDoubleToDouble = new Function[Double, Double] {
    require(args.size < 1, "not enough arguments")
    require(args.size > 1, "too many arguments")
    override def apply(x: Double): Double = {
      engine.put(args.head, x)
      engine.eval(func).asInstanceOf[Double]
    }
  }
}
