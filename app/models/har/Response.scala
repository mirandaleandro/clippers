package models.har

import net.fwbrasil.activate.entity.Entity
import play.api.libs.json.JsObject

/**
  * Created by Leo on 4/15/2016.
  */
class Response(var status: Int,
               var statusText: String,
               var contentSize: Long,
               var mimeType: String,
               var redirectUrl: String,
               var headersSize: Int,
               var bodySize: Int,
               var transferSize: Int
              ) extends Entity

object Response {

  def apply(json: JsObject) = new Response(
    status = (json \ "status").as[Int],
    statusText = (json \ "statusText").as[String],
    contentSize = (json \ "content" \ "size").as[Long],
    mimeType = (json \ "content" \ "mimeType").as[String],
    redirectUrl = (json \ "redirectURL").as[String],
    headersSize = (json \ "headersSize").as[Int],
    bodySize = (json \ "bodySize").as[Int],
    transferSize = (json \ "_transferSize").as[Int]
  )

}