package models.project

import models.har.Page
import net.fwbrasil.activate.entity.Entity
import play.api.libs.json.JsValue

/**
  * Created by Leo on 4/18/2016.
  */
class Project(var name:String, var workNotes:String, var pages:List[Page] = List.empty[Page]) extends Entity

object Project{

  def apply(json:JsValue): Project = {

    val pages = Page.getPages(harJson = (json \ "harJson"))

    new Project(
      name = (json \ "projectName").as[String],
      workNotes = (json \ "workNotes").as[String],
      pages = pages
    )
  }
}