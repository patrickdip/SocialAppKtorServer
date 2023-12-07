package com.mrdip.repository.post

import com.mrdip.model.PostResponse
import com.mrdip.model.PostTextParams
import com.mrdip.model.PostsResponse
import com.mrdip.util.Response

interface PostRepository {
    suspend fun createPost(imageUrl: String, postTextParams: PostTextParams): Response<PostResponse>

    suspend fun getFeedPosts(userId: Long, pageNumber: Int, pageSize: Int): Response<PostsResponse>

    suspend fun getPostsByUser(
        postsOwnerId: Long,
        currentUserId: Long,
        pageNumber: Int,
        pageSize: Int
    ): Response<PostsResponse>

    suspend fun getPost(postId: Long, currentUserId: Long): Response<PostResponse>

    suspend fun deletePost(postId: Long): Response<PostResponse>
}