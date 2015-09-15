package util

import akka.actor.{ActorLogging, Actor}

class UtilManager extends Actor with ActorLogging {
  def receive = {
    case any => log.debug("unsupported message: " + any)
  }
}

object UtilManager {

}
