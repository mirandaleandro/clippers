package controllers.util

import controllers.AppController._
import play.api.mvc.Action

/**
  * Created by Leo on 4/18/2016.
  */
trait Cors {
  def headers = List(
    "Access-Control-Allow-Origin" -> "*",
    "Access-Control-Allow-Methods" -> "*",
    "Access-Control-Max-Age" -> "3600",
    "Access-Control-Allow-Headers" -> "Access-Control-Allow-Origin,Origin,X-Requested-With, Content-Type, Accept, Authorization",
    "Access-Control-Allow-Credentials" -> "true"
  )

  def options = Action { request =>
    NoContent.withHeaders(headers : _*)
  }
}
