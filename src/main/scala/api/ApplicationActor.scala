package api

import spray.routing.{HttpService, HttpServiceActor}

class ApplicationActor extends HttpServiceActor with ApplicationAPI {
  def receive = runRoute(routes)
}

trait ApplicationAPI extends HttpService {

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

  val routes = index ~ statics
}

