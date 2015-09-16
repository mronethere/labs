package util.expression

import scala.util.Try

/**
 * Function executor
 */
class FunctionExecutor(func: String, args: Seq[String],
                       scriptEngine: ScriptEngineCustom = new ScalaScriptEngine()) {

  private val engine = scriptEngine.engine

  require(args.forall(func.contains), "func must contain all defined args")

  /**
   * Adds asInstanceOf to all params to make code compilable
   */
  private val newFunc = {
    def interpolate(str: String, arg: String) = str.replace(arg, arg + ".asInstanceOf[Double]")
    var ret = func
    args.foreach { arg =>
      ret = interpolate(ret, arg)
    }
    ret
  }

  /**
   * Substitutes `args` by `values` in `func` and executes it.
   *
   * @param values values
   * @return result of evaluating
   */
  def evalUnsafe(values: Seq[Double]): Any = {
    require(values.size == args.size, "wrong values length")
    (args zip values) foreach {
      case (arg, value) => engine.put(arg, value)
    }
    engine.eval {
      s"""
        |import Math._
        |
        |$newFunc
      """.stripMargin
    }
  }

  /**
   * Wraps `evalUnsafe` within Try
   *
   * @see evalUnsafe
   */
  def eval(values: Seq[Double]): Try[Any] = Try(evalUnsafe(values))

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
