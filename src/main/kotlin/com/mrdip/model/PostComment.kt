package com.mrdip.model

import kotlinx.serialization.Serializable

@Serializable
data class NewCommentParams(
    val content: String,
    val postId: Long,
    val userId: Long
)

@Serializable
data class RemoveCommentParams(
    val postId: Long,
    val commentId: Long,
    val userId: Long
)

@Serializable
data class PostComment(
    val commentId: Long,
    val content: String,
    val postId: Long,
    val userId: Long,
    val userName: String,
    val userImageUrl: String?,
    val createdAt: String
)

@Serializable
data class CommentResponse(
    val success: Boolean,
    val comment: PostComment? = null,
    val message: String? = null
)

@Serializable
data class GetCommentsResponse(
    val success: Boolean,
    val comments: List<PostComment> = listOf(),
    val message: String? = null
)












