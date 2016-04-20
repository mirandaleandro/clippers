package controllers.util

import akka.actor.Status.Failure
import akka.actor.Status.Success
import models.postgresqlContext._
import models.user.User
import net.fwbrasil.radon.transaction.{TransactionalExecutionContext, TransactionType}
import play.api.libs.json.JsValue
import play.api.mvc._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.Try

/**
  * Created by Leo on 2/20/2016.
  */
object TransactionalAsyncAction extends Controller{

  def getAction[T](transactionType: TransactionType)(f:(Request[JsValue]) => (TransactionalExecutionContext) => Future[Result]) = {
    Action.async(parse.json(maxLength = 1024 * 1024 * 10)) (request =>
      asyncTransactionalChain(transactionType)( implicit ctx =>
        f(request)(ctx)
      )
    )
  }

  def apply(f:(Request[JsValue]) => (TransactionalExecutionContext) => Future[Result])(implicit transactionType: TransactionType = readWrite) =
    getAction[JsValue](transactionType)(f)

  def getAction[T](bodyParser : play.api.mvc.BodyParser[T], transactionType: TransactionType)(f:(Request[T]) => (TransactionalExecutionContext) => Future[Result]) = {
    Action.async(bodyParser)(request =>
      asyncTransactionalChain(transactionType)( implicit ctx =>
        f(request)(ctx)
      )
    )
  }

  def anyContent(f:(Request[AnyContent]) => (TransactionalExecutionContext) => Future[Result])(implicit transactionType: TransactionType = readWrite) =
    getAction[AnyContent](play.api.mvc.BodyParsers.parse.anyContent,transactionType)(f)


  def authenticated(f:(User) => (Request[AnyContent]) => (TransactionalExecutionContext) => Future[Result])(implicit transactionType: TransactionType = readWrite) = {
    Action.async(play.api.mvc.BodyParsers.parse.anyContent)(request =>
      asyncTransactionalChain(transactionType)( implicit ctx => {

        //TODO do the actual logic here that  will allow us to fetch the properuserfrom the database
        val futureUser: Future[Option[User]] = User.findUserWithName("Leo")

        val optionUser: Option[User] = Await.result(futureUser.map(optUser => optUser), Duration.Inf)

         optionUser.map { user =>
          f(user)(request)(ctx)
        }.getOrElse(Future.successful(BadRequest("User Not Authorized")))
      }
      )
    )
  }
}