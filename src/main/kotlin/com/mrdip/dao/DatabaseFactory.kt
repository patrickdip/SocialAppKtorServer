package com.mrdip.dao

import com.mrdip.dao.follows.FollowsTable
import com.mrdip.dao.post.PostTable
import com.mrdip.dao.post_likes.PostLikesTable
import com.mrdip.dao.user.UserRow
import com.mrdip.dao.user.UserTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init(){
        Database.connect(createHikariDataSource())
        transaction {
            SchemaUtils.create(UserTable, FollowsTable, PostTable, PostLikesTable)
        }
    }

    private fun createHikariDataSource(): HikariDataSource{
        val driverClass = "org.postgresql.Driver"
        val jdbcUrl = "jdbc:postgresql://localhost:5432/socialmediadb"

        val hikariConfig = HikariConfig().apply {
            driverClassName = driverClass
            setJdbcUrl(jdbcUrl)
            maximumPoolSize = 3
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }

        return HikariDataSource(hikariConfig)
    }

    suspend fun <T> dbQuery(block: suspend () -> T) =
        newSuspendedTransaction(Dispatchers.IO) { block()  }
}













