package com.mrdip.model

import kotlinx.serialization.Serializable

@Serializable
data class LikeParams(
    val postId: Long,
    val userId: Long
)

@Serializable
data class LikeResponse(
    val success: Boolean,
    val message: String? = null
)