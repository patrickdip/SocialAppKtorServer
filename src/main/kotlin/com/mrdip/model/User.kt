package com.mrdip.model

import org.jetbrains.exposed.sql.Table

object UserRow: Table(name = "users"){
    val id = integer(name = "user_id").autoIncrement()
    val name = varchar(name = "user_name", length = 250)
    val email = varchar(name = "user_email", length = 250)
    val bio = text(name = "user_bio").default(
        defaultValue = "Hey, what's up? Welcome to my SocialApp page!"
    )
    val password = varchar(name = "user_password", length = 100)
    val avatar = text(name = "user_avatar").nullable()

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(id)
}

data class User(
    val id: Int,
    val name: String,
    val bio: String,
    val avatar: String?,
    val password: String
)















