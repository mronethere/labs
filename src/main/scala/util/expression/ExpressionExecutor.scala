package util.expression

/**
 * Expression executor
 */
class ExpressionExecutor(scriptEngine: ScriptEngineCustom = new ScalaScriptEngine()) {

  private val engine = scriptEngine.engine

  def eval(expr: String): Any = engine.eval(expr)
}