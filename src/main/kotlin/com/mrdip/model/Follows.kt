package com.mrdip.model

import kotlinx.serialization.Serializable

@Serializable
data class FollowAndUnfollowResponse(
    val success: Boolean,
    val message: String? = null
)

@Serializable
data class FollowsParams(
    val follower: Long,
    val following: Long,
    val isFollowing: Boolean
)