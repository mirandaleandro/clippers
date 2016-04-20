package models.har

import org.joda.time.DateTime
import play.api.libs.json.{JsValue, JsArray, JsObject}
import models.postgresqlContext._
import models.postgresqlContext
import spray.json.DefaultJsonProtocol
import DefaultJsonProtocol._


/**
  * Created by Leo on 4/15/2016.
  */
class Page(var pageId:String,
           var startedDateTime:DateTime,
           var url:String,
           var onContentLoad:Float,
           var onLoad:Option[Float],
           var entries:List[Entry] = List.empty[Entry]) extends Entity{

}

object Page{

  def apply( json:JsObject) = {
    new Page(
      pageId = (json \ "id").as[String],
      startedDateTime = new DateTime( (json \ "startedDateTime").as[String] ),
      url = (json \ "title").as[String],
      onContentLoad = (json \ "pageTimings" \ "onContentLoad").as[Float],
      onLoad = (json \ "pageTimings" \ "onLoad").as[Option[Float]]
    )
  }


  def getPages(harJson: JsValue) = {

    val pages: List[Page] = (harJson \ "log" \ "pages").as[List[JsObject]].map(pageJson => Page(pageJson))
    val entries = Entry.getEntries((harJson \ "log" \ "entries"))

    pages.foreach( page =>
      page.entries = entries.filter{ entry =>
        page.pageId == entry.pageRef
      }
    )

    pages
  }

  def getPagesAsJson(json: JsValue) = getPages(json).map(_.toJson(fullDepth)).toJson
}
