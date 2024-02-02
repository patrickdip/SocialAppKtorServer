package com.mrdip.repository.post_comments

import com.mrdip.model.CommentResponse
import com.mrdip.model.GetCommentsResponse
import com.mrdip.model.NewCommentParams
import com.mrdip.model.RemoveCommentParams
import com.mrdip.util.Response

interface PostCommentsRepository {
    suspend fun addComment(params: NewCommentParams): Response<CommentResponse>

    suspend fun removeComment(params: RemoveCommentParams): Response<CommentResponse>

    suspend fun getPostComments(postId: Long, pageNumber: Int, pageSize: Int): Response<GetCommentsResponse>
}