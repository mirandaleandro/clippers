package models.har

import net.fwbrasil.activate.entity.Entity
import play.api.libs.json.JsObject
import models.postgresqlContext._

/**
  * Created by Leo on 4/15/2016.
  */
class Timing(var blocked: Float,
             var dns: Float,
             var connect: Float,
             var send: Float,
             var waitTime: Float,
             var receive: Float,
             var ssl: Float) extends Entity

object Timing{

  def apply( json:JsObject) = new Timing(
    blocked = (json \ "blocked").as[Float],
    dns = (json \ "dns").as[Float],
    connect = (json \ "connect").as[Float],
    send = (json \ "send").as[Float],
    waitTime = (json \ "wait").as[Float],
    receive = (json \ "receive").as[Float],
    ssl = (json \ "ssl").as[Float]
  )

}
