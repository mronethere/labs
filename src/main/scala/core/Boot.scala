package core

import akka.actor.{Props, ActorSystem}
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import api.ApplicationActor
import spray.can.Http

import scala.concurrent.duration._

object Boot extends App {

  /*implicit val system = ActorSystem("labs")
  implicit val timeout = Timeout(5.seconds)

  val application = system.actorOf(Props[ApplicationActor], "app-service")

  IO(Http) ? Http.Bind(application, interface = "localhost", port = 9000)*/

  import game._
  /*Utils.shapes.foreach { shape =>
    println(s"--------\n$shape ->")
    shape.opts.foreach(Utils.printShape)
  }*/
  Utils.printMatrix(Field(10))
  Utils.printMatrix(Field(10).insert(Point(3, 2, 6)).insert(Point(0, 1, 4)))
}
