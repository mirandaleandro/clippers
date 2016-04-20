package controllers

import controllers.util.Cors
import play.api.mvc.{Action, Controller}
import models.postgresqlContext._

object AppController extends Controller with Cors{

  def healthCheck() = Action{
    transactional {
      Ok("The application is alive and healthy")
    }
  }

}
