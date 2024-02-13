package com.mrdip.repository.post_likes

import com.mrdip.model.LikeParams
import com.mrdip.model.LikeResponse
import com.mrdip.util.Response

interface PostLikesRepository {
    suspend fun addLike(params: LikeParams): Response<LikeResponse>

    suspend fun removeLike(params: LikeParams): Response<LikeResponse>
}