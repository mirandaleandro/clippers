package controllers

import controllers.util.{Cors, TransactionalAsyncAction}
import models.har.{Page}
import models.project.Project
import net.fwbrasil.radon.transaction.TransactionalExecutionContext
import play.api.mvc.{Controller}
import models.postgresqlContext._

object ProjectController extends Controller with Cors{

  def getProject(projectId:String) = TransactionalAsyncAction.anyContent {
    request =>
      implicit ctx: TransactionalExecutionContext =>
          asyncById[Project](projectId).map( optProject =>
            optProject.map{ project =>
              Ok(project.toJsonString(fullDepth)).withHeaders(headers : _*)
            }getOrElse(NotFound(s"Could not find project with id $projectId"))
          )
  }

  def addPage() = TransactionalAsyncAction {
    request =>
      implicit ctx: TransactionalExecutionContext =>
        asyncTransactional {
          Ok(Page.getPagesAsJson(request.body).toString()).withHeaders(headers : _*)
        }
  }

  def addProject() = TransactionalAsyncAction {
    request =>
      implicit ctx: TransactionalExecutionContext =>
        asyncTransactional {
          Ok(Project(request.body).toJsonString).withHeaders(headers : _*)
        }
  }
}
