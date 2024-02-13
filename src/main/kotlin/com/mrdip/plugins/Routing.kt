package com.mrdip.plugins

import com.mrdip.route.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.http.content.static

fun Application.configureRouting() {
    routing {
        authRouting()
        followsRouting()
        postRouting()
        profileRouting()
        postCommentsRouting()
        postLikesRouting()
        static {
            resources("static")
        }
    }
}
