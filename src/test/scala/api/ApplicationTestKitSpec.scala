package api

import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
import spray.http.StatusCodes._

class ApplicationTestKitSpec extends Specification with Specs2RouteTest with ApplicationAPI {
  def actorRefFactory = system

  "App service" should {
    "return index page" in {
      Get() ~> index ~> check {
        responseAs[String] must contain("<!DOCTYPE html>")
      }
    }
  }
}
