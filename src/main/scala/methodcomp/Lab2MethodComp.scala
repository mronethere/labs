package methodcomp

import akka.actor.Status.Success
import api.{LabController, LabData}
import util.expression.ExpressionExecutor

import scala.concurrent.Future
import core.Boot.system.dispatcher

object Lab2MethodComp extends LabController {
  def solve(data: LabData): Future[List[String]] = Future {
    val expression = createScript(data.params.head, data.params(1),
      data.params(2), data.params(3), data.params(4))
    List(new ExpressionExecutor().eval(expression).asInstanceOf[String])
  }

  //TODO I got an idea, to pull it via reflection or through classloader
  def createScript(e: String, swap: String, func: String, a: String, b: String) =
    s"""
       |def chords(e: Double, swap: Boolean, fun: Double => Double, _a: Double, _b: Double) = {
       |  def iter(a: Double, b: Double): Double = {
       |    val x = a - (fun(a) * (b - a)) / (fun(b) - fun(a))
       |    if (Math.abs(x - a) < e) x
       |    else iter(x, b)
       |  }
       |  var args = (_a, _b)
       |  if (swap) args = args.swap
       |  iter(args._1, args._2)
       |}
       |val fun = (x: Double) => $func
       |chords($e.asInstanceOf[Double], $swap.asInstanceOf[Boolean], fun, $a.asInstanceOf[Double], $b.asInstanceOf[Double])
     """.stripMargin
}
