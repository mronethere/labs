package util

import akka.actor.{ActorLogging, Actor}
import util.UtilManager._

class UtilManager extends Actor with ActorLogging {
  def receive = {
    case GiveExpressionExecutor => log.info("gave expression executor")
    case any => log.debug("unsupported message: " + any)
  }
}

object UtilManager {
  case object GiveExpressionExecutor
}
