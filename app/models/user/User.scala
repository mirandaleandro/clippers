package models.user

import models.postgresqlContext._
import net.fwbrasil.radon.transaction.TransactionalExecutionContext
import play.api.libs.Crypto

class User
(
  var email:String,
  var token: String,
  var name: String
) extends Entity
{
    def signedToken = Crypto.signToken(id)
}

object User
{
    def apply( email:String, token:String, name:String ) = new User(email = email, token = token, name = name)

    //TODO add actual logic to get users
    def findUserWithName(name:String)(implicit ctx:TransactionalExecutionContext)= asyncAll[User].map(_.headOption)
}