package models.har

import models.postgresqlContext._
import net.fwbrasil.activate.entity.Entity
import play.api.libs.json.JsObject

/**
  * Created by Leo on 4/15/2016.
  */
class Request(
               var method:String,
               var url:String,
               var headersSize:Long,
               var bodySize:Long
             ) extends Entity{

  var headersCount:Int = 0
  var queryStringCount: Int = 0
  var cookiesCount:Int = 0

  def setCounters(json:JsObject): Unit ={
    //TODO
  }
}

object Request{

  def apply(json:JsObject) = {
    val request = new Request(
      method = (json \ "method").as[String],
      url = (json \ "url").as[String],
      headersSize = (json \ "headersSize").as[Long],
      bodySize = (json \ "bodySize").as[Long]
    )
    request.setCounters(json)
    request
  }

}
