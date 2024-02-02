package com.mrdip.repository.post_comments

import com.mrdip.dao.post.PostDao
import com.mrdip.dao.post_comments.PostCommentRow
import com.mrdip.dao.post_comments.PostCommentsDao
import com.mrdip.model.*
import com.mrdip.util.Response
import io.ktor.http.*

class PostCommentsRepositoryImpl(
    private val commentsDao: PostCommentsDao,
    private val postDao: PostDao
) : PostCommentsRepository {
    override suspend fun addComment(params: NewCommentParams): Response<CommentResponse> {
        val postCommentRow = commentsDao.addComment(
            postId = params.postId,
            userId = params.userId,
            content = params.content
        )

        return if (postCommentRow == null){
            Response.Error(
                code = HttpStatusCode.Conflict,
                data = CommentResponse(success = false, message = "Could not insert comment into the db")
            )
        }else{
            postDao.updateCommentsCount(postId = params.postId)
            Response.Success(
                data = CommentResponse(success = true, comment = toPostComment(postCommentRow))
            )
        }
    }

    override suspend fun removeComment(params: RemoveCommentParams): Response<CommentResponse> {
        val commentRow = commentsDao.findComment(commentId = params.commentId, postId = params.postId)

        return if (commentRow == null){
            Response.Error(
                code = HttpStatusCode.NotFound,
                data = CommentResponse(success = false, message = "Comment ${params.commentId} not found")
            )
        }else{
            val postOwnerId = postDao.getPost(postId = params.postId)!!.userId

            if (params.userId != commentRow.userId && params.userId != postOwnerId){
                Response.Error(
                    code = HttpStatusCode.Forbidden,
                    data = CommentResponse(
                        success = false,
                        message = "User ${params.userId} cannot delete comment ${params.commentId}"
                    )
                )
            }else{
                val commentWasRemoved = commentsDao.removeComment(commentId = params.commentId, postId = params.postId)

                if (commentWasRemoved){
                    postDao.updateCommentsCount(postId = params.postId, decrement = true)
                    Response.Success(
                        data = CommentResponse(success = true)
                    )
                }else{
                    Response.Error(
                        code = HttpStatusCode.Conflict,
                        data = CommentResponse(
                            success = false,
                            message = "Comment ${params.commentId} could not be removed"
                        )
                    )
                }
            }
        }
    }

    override suspend fun getPostComments(postId: Long, pageNumber: Int, pageSize: Int): Response<GetCommentsResponse> {
        val commentRows = commentsDao.getComments(postId = postId, pageNumber = pageNumber, pageSize = pageSize)
        val comments = commentRows.map {
            toPostComment(it)
        }

        return Response.Success(
            data = GetCommentsResponse(success = true, comments = comments)
        )
    }

    private fun toPostComment(commentRow: PostCommentRow): PostComment{
        return PostComment(
            commentId = commentRow.commentId,
            content = commentRow.content,
            postId = commentRow.postId,
            userId = commentRow.userId,
            userName = commentRow.userName,
            userImageUrl = commentRow.userImageUrl,
            createdAt = commentRow.createdAt,
        )
    }
}







