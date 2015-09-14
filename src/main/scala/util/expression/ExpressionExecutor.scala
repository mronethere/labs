package util.expression

import scala.util.Try

/**
 * Scala expression executor
 */
class ExpressionExecutor {

  private val engine = new ScalaScriptEngine().engine

  def eval(expr: String): Try[Any] = Try {
    engine.eval(expr)
  }
}