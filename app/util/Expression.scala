package util

import javax.script.ScriptEngineManager

import scala.util.{Failure, Success, Try}

/**
 * Representing scala expression
 */
class Expression() {

  private val engine = new ScriptEngineManager().getEngineByName("scala")
  private val settings = engine.asInstanceOf[scala.tools.nsc.interpreter.IMain].settings
  settings.embeddedDefaults[Expression]
  settings.usejavacp.value = true


  def eval(expr: String, vars: Any*): Try[AnyRef] = Try {
    null
  }


}

object Expression extends App {

}