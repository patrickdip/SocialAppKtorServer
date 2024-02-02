package com.mrdip.route

import com.mrdip.model.CommentResponse
import com.mrdip.model.GetCommentsResponse
import com.mrdip.model.NewCommentParams
import com.mrdip.model.RemoveCommentParams
import com.mrdip.repository.post_comments.PostCommentsRepository
import com.mrdip.util.Constants
import com.mrdip.util.getLongParameter
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Routing.postCommentsRouting(){
    val repository by inject<PostCommentsRepository>()

    authenticate {
        route(path = "/post/comments"){

            post(path = "/create"){
                try {
                    val params = call.receiveNullable<NewCommentParams>()

                    if (params == null){
                        call.respond(
                            status = HttpStatusCode.BadRequest,
                            message = CommentResponse(
                                success = false,
                                message = "Could not parse comment parameters"
                            )
                        )
                        return@post
                    }

                    val result = repository.addComment(params = params)
                    call.respond(status = result.code, message = result.data)
                }catch (anyError: Throwable){
                    call.respond(
                        status = HttpStatusCode.InternalServerError,
                        message = CommentResponse(
                            success = false,
                            message = "An unexpected error occurred, try again!"
                        )
                    )
                    return@post
                }
            }

            delete(path = "/delete"){
                try {
                    val params = call.receiveNullable<RemoveCommentParams>()
                    if (params == null){
                        call.respond(
                            status = HttpStatusCode.BadRequest,
                            message = CommentResponse(
                                success = false,
                                message = "Could not parse comment parameters"
                            )
                        )
                        return@delete
                    }

                    val result = repository.removeComment(params = params)
                    call.respond(status = result.code, message = result.data)
                }catch (error: Throwable){
                    call.respond(
                        status = HttpStatusCode.InternalServerError,
                        message = CommentResponse(
                            success = false,
                            message = "An unexpected error occurred, try again!"
                        )
                    )
                    return@delete
                }
            }

            get(path = "/{postId}"){
                try {
                    val postId = call.getLongParameter(name = "postId")
                    val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 0
                    val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: Constants.DEFAULT_PAGE_SIZE

                    val result = repository.getPostComments(postId = postId, pageNumber = page, pageSize = limit)
                    call.respond(status = result.code, message = result.data)
                }catch (anyError: Throwable){
                    call.respond(
                        status = HttpStatusCode.InternalServerError,
                        message = GetCommentsResponse(
                            success = false,
                            message = "An unexpected error occurred, try again!"
                        )
                    )
                    return@get
                }
            }
        }
    }
}