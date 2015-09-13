package util.expression

import javax.script.ScriptEngineManager

private[util] class ScalaEngine {
  private val engine = new ScriptEngineManager().getEngineByName("scala")
  private val settings = engine.asInstanceOf[scala.tools.nsc.interpreter.IMain].settings
  settings.embeddedDefaults[ExpressionExecutor]
  settings.usejavacp.value = true
}
