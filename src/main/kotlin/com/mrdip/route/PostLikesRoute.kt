package com.mrdip.route

import com.mrdip.model.LikeParams
import com.mrdip.model.LikeResponse
import com.mrdip.repository.post_likes.PostLikesRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Routing.postLikesRouting(){
    val repository by inject<PostLikesRepository>()

    authenticate {
        route(path = "post/likes"){
            post(path = "/add"){
                try {
                    val params = call.receiveNullable<LikeParams>()
                    if (params == null){
                        call.respond(
                            status = HttpStatusCode.BadRequest,
                            message = LikeResponse(
                                success = false,
                                message = "Could not parse like parameters"
                            )
                        )
                        return@post
                    }

                    val result = repository.addLike(params = params)
                    call.respond(status = result.code, message = result.data)
                }catch (error: Throwable){
                    call.respond(
                        status = HttpStatusCode.InternalServerError,
                        message = LikeResponse(
                            success = false,
                            message = "An unexpected error occurred, try again!"
                        )
                    )
                }
            }

            delete(path = "/remove"){
                try {
                    val params = call.receiveNullable<LikeParams>()
                    if (params == null){
                        call.respond(
                            status = HttpStatusCode.BadRequest,
                            message = LikeResponse(
                                success = false,
                                message = "Could not parse like parameters"
                            )
                        )
                        return@delete
                    }

                    val result = repository.removeLike(params = params)
                    call.respond(status = result.code, message = result.data)
                }catch (error: Throwable){
                    call.respond(
                        status = HttpStatusCode.InternalServerError,
                        message = LikeResponse(
                            success = false,
                            message = "An unexpected error occurred, try again!"
                        )
                    )
                }
            }
        }
    }
}