package util.expression

import org.specs2.mutable.Specification

import scala.util.Try

class ScriptEngineSpec extends Specification {
  "in ScalaScriptEngine" should {
    "engine must not return null" in {
      new ScalaScriptEngineMock().engine must not beNull
    }
  }

  "ExpressionExecutor" should {
    val ee = new ExpressionExecutor(new ScalaScriptEngineMock)
    "executes simple expressions" in {
      ee.eval("1 + 1") mustEqual Try(2)
      ee.eval("List(1, 2, 3, 4, 5).filter(_ % 2 == 0).size") mustEqual Try(2)
    }
    "handle errors" in {
      ee.eval("no compilable code").isFailure must beTrue
    }
  }
}
