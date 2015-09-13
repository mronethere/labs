package util

import javax.script.ScriptEngineManager

/**
 * Representing scala expression
 *
 * @param expr expression
 * @param params parameters
 */
class Expression(expr: String, params: List[String]) {

  val engine = Expression.scalaEngineFactory

  /**
   * Evaluates the expression `expr`
   *
   * @param vars - valued params
   * @return result of expression
   */
  def eval(vars: Any*): Any = {
    require(params.size == vars.size, "wrong params count")
    (params zip vars) foreach {
      case (param, value) => engine.put(param, value)
    }
    engine.eval(expr)
  }


}

object Expression {

  lazy val SupportedTypes = List("int", "double")

  def scalaEngineFactory = new ScriptEngineManager().getEngineByName("scala")

}