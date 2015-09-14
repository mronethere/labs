package util.expression

import scala.util.Try

/**
 * Scala expression executor
 *
 * @param expr expression
 * @param params parameters
 */
case class ExpressionExecutor(expr: String, params: Seq[String]) {
  private val engine = new ScalaEngine().engine

  /**
   * Evaluates `expr` by replacing `params` to given `values`
   *
   * @param values values
   * @return Try of expression result, obviously this may fail
   */
  def eval(values: Seq[String]): Try[Any] = Try {
    require(params.size == values.size, "wrong params count")
    (params zip values) foreach {
      case (param, value) => engine.put(param, value)
    }
    engine.eval(expr)
  }
}