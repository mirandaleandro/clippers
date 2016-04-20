package models

import models.user.User
import net.fwbrasil.activate.migration.Migration
import models.postgresqlContext._

class CreateSchema extends Migration {

  def timestamp = 201602280040l

  def up = {
    removeAllEntitiesTables
      .ifExists
      .cascade
    createTableForAllEntities
      .ifNotExists

  }

}

class SeedData extends Migration {

  def timestamp = 201602280041l

  def up = {
    customScript {
      User("mirandaleandro@gmail.com", "leosToken", "Leandro Miranda")
    }
  }
}