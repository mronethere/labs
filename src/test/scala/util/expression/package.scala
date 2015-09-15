package util

import javax.script.ScriptEngineManager

package object expression {
  class ScalaScriptEngineMock private[util]() extends ScriptEngineCustom {
    val engine = new ScriptEngineManager().getEngineByName("scala")
    val settings = engine.asInstanceOf[scala.tools.nsc.interpreter.IMain].settings
    settings.embeddedDefaults[ScalaScriptEngineMock]
  }
}
