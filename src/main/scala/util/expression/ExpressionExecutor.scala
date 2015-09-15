package util.expression

import scala.util.Try

/**
 * Expression executor
 */
class ExpressionExecutor(scriptEngine: ScriptEngineCustom = new ScalaScriptEngine()) {

  private val engine = scriptEngine.engine

  def eval(expr: String): Try[Any] = Try {
    engine.eval(expr)
  }
}