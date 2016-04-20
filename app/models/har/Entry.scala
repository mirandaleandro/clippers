package models.har

import models.postgresqlContext._
import net.fwbrasil.activate.entity.Entity
import org.joda.time.DateTime
import play.api.libs.json.{JsArray, JsValue, JsObject}

/**
  * Created by Leo on 4/15/2016.
  */
class Entry(
             var startedDateTime: DateTime,
             var time: Float,
             var request: Request,
             var response: Response,
             var timings: Timing,
             var pageRef: String
           ) extends Entity

object Entry {
  def apply(json: JsObject) = {

    val request = Request((json \ "request").as[JsObject])
    val response = Response((json \ "response").as[JsObject])
    val timings = Timing((json \ "timings").as[JsObject])

    new Entry(
      startedDateTime = new DateTime((json \ "startedDateTime").as[String]),
      time = (json \ "time").as[Float],
      request = request,
      response = response,
      timings = timings,
      pageRef = (json \ "pageref").as[String]
    )
  }

  def getEntries(json: JsValue) = json.as[List[JsObject]].map(Entry(_))

}

