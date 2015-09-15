package util.expression

import org.specs2.mutable.Specification

class ScriptEngineSpec extends Specification {
  "in ScalaScriptEngine" should {
    "engine must not return null" in {
      new ScalaScriptEngine().engine must not beNull
    }
  }
}
