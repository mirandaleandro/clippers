package models

import net.fwbrasil.activate.json.spray.SprayJsonContext

import net.fwbrasil.activate.ActivateContext
import com.github.mauricio.async.db.Configuration
import com.github.mauricio.async.db.postgresql.pool.PostgreSQLConnectionFactory
import net.fwbrasil.activate.storage.relational.async.AsyncPostgreSQLStorage

object postgresqlContext extends ActivateContext with SprayJsonContext{
    val storage = new AsyncPostgreSQLStorage {
        def configuration =
            new Configuration(
                username = "postgres",
                host = "127.0.0.1",
                password = Some("abc123"),
                database = Some("clippers"))
        lazy val objectFactory = new PostgreSQLConnectionFactory(configuration)
    }
}