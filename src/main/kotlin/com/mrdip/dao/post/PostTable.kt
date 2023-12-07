package com.mrdip.dao.post

import com.mrdip.dao.user.UserTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime

object PostTable: Table(name = "posts"){
    val postId = long(name = "post_id").uniqueIndex()
    val caption = varchar(name = "caption", length = 300)
    val imageUrl = varchar(name = "image_url", length = 300)
    val likesCount = integer(name = "likes_count")
    val commentsCount = integer(name = "comments_count")
    val userId = long(name = "user_id").references(ref = UserTable.id, onDelete = ReferenceOption.CASCADE)
    val createdAt = datetime(name = "created_at").defaultExpression(defaultValue = CurrentDateTime)
}

data class PostRow(
    val postId: Long,
    val caption: String,
    val imageUrl: String,
    val createdAt: String,
    val likesCount: Int,
    val commentsCount: Int,
    val userId: Long,
    val userName: String,
    val userImageUrl: String?
)