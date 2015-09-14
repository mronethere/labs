package util.expression

import javax.script.ScriptEngineManager


class ScalaScriptEngine private[util]() {
  val engine = new ScriptEngineManager().getEngineByName("scala")
  val settings = engine.asInstanceOf[scala.tools.nsc.interpreter.IMain].settings
  settings.embeddedDefaults[ScalaScriptEngine]
  settings.usejavacp.value = true
}
