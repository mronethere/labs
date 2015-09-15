package util.expression

import javax.script.{ScriptEngine, ScriptEngineManager}


trait ScriptEngineCustom {
  def engine: ScriptEngine
}

class ScalaScriptEngine private[util]() extends ScriptEngineCustom {
  val engine = new ScriptEngineManager().getEngineByName("scala")
  val settings = engine.asInstanceOf[scala.tools.nsc.interpreter.IMain].settings
  settings.embeddedDefaults[ScalaScriptEngine]
  settings.usejavacp.value = true
}


