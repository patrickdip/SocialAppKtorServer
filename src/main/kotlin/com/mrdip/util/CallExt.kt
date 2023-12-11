package com.mrdip.util

import com.mrdip.model.PostResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.response.*

suspend fun ApplicationCall.getLongParameter(name: String, isQueryParameter: Boolean = false): Long{
    val parameter = if (isQueryParameter){
        request.queryParameters[name]?.toLongOrNull()
    }else{
        parameters[name]?.toLongOrNull()
    } ?: kotlin.run {
        respond(
            status = HttpStatusCode.BadRequest,
            message = PostResponse(
                success = false,
                message = "Parameter $name is missing or invalid"
            )
        )
        throw BadRequestException("Parameter $name is missing or invalid")
    }
    return parameter
}