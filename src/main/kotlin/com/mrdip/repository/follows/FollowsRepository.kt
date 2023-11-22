package com.mrdip.repository.follows

import com.mrdip.model.FollowAndUnfollowResponse
import com.mrdip.util.Response

interface FollowsRepository {
    suspend fun followUser(follower: Long, following: Long): Response<FollowAndUnfollowResponse>

    suspend fun unfollowUser(follower: Long, following: Long): Response<FollowAndUnfollowResponse>
}