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
      ee.eval("1 + 1") mustEqual 2
      ee.eval("List(1, 2, 3, 4, 5).filter(_ % 2 == 0).size") mustEqual 2
    }
    "handle errors" in {
      val ee = new ExpressionExecutor(new ScalaScriptEngineMock)
      Try(ee.eval("weird code")) must beFailedTry
    }
  }

  "FunctionExecutor" should {
    "eval simple math expr" in {
      val fe = new FunctionExecutor("sqrt(4) - round(1.9)", Nil, new ScalaScriptEngineMock)
      fe.eval(Nil) mustEqual Try(0)
    }
    "substitute args" in {
      val fe = new FunctionExecutor("sqrt(x) - round(y)", List("x", "y"), new ScalaScriptEngineMock)
      fe.eval(List(4, 1.9)) mustEqual Try(0)
    }
    "converts to scala (Double => Double)" in {
      val fe = new FunctionExecutor("sqrt(x) + pow(2, x)", List("x"), new ScalaScriptEngineMock)
      val func = fe.toFunctionDoubleToDouble
      func(1) mustEqual 3
      func(4) mustNotEqual 1
    }
  }
}
