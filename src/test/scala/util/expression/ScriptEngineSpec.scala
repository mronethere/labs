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
    "executes simple expressions" in {
      val ee = new ExpressionExecutor(new ScalaScriptEngineMock)
      ee.eval("1 + 1") mustEqual Try(2)
      ee.eval("List(1, 2, 3, 4, 5).filter(_ % 2 == 0).size") mustEqual Try(2)
    }
    "handle errors" in {
      val ee = new ExpressionExecutor(new ScalaScriptEngineMock)
      ee.eval("weird code").isFailure must beTrue
    }
  }
}
