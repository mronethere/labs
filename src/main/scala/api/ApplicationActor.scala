package api

import spray.routing.{HttpService, HttpServiceActor}
import spray.httpx.SprayJsonSupport.sprayJsonMarshaller
import spray.httpx.SprayJsonSupport.sprayJsonUnmarshaller
import spray.json.DefaultJsonProtocol

import core.Boot.system.dispatcher


class ApplicationActor extends HttpServiceActor with ApplicationAPI {
  def receive = runRoute(routes)
}

trait ApplicationAPI extends HttpService with DefaultJsonProtocol {

  implicit val labDataFormat = jsonFormat4(LabData)

  val index = pathEndOrSingleSlash {
    get {
      getFromResource("app/index.html")
    }
  }

  val statics =
    pathPrefix("app") {
      getFromResourceDirectory("app")
    } ~
    pathPrefix("bower_components") {
      getFromResourceDirectory("bower_components")
    }

  val labs =
    path("lab") {
      post {
        entity(as[LabData]) { labData =>
          onSuccess(LabManager.solveLab(labData))(complete(_))
        }
      }
    }

  val routes = index ~ statics ~ labs
}

case class LabData(projectName: String, labId: Int, params: List[String], isSuccess: Boolean)
