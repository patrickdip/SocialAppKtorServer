package com.mrdip.dao.post

interface PostDao{
    suspend fun createPost(caption: String, imageUrl: String, userId: Long): PostRow?

    suspend fun getFeedsPost(userId: Long, follows: List<Long>, pageNumber: Int, pageSize: Int): List<PostRow>

    suspend fun getPostByUser(userId: Long, pageNumber: Int, pageSize: Int): List<PostRow>

    suspend fun getPost(postId: Long): PostRow?

    suspend fun updateLikesCount(postId: Long, decrement: Boolean = false): Boolean

    suspend fun updateCommentsCount(postId: Long, decrement: Boolean = false): Boolean

    suspend fun deletePost(postId: Long): Boolean
}