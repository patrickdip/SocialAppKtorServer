package com.mrdip.plugins

import com.mrdip.route.authRouting
import com.mrdip.route.followsRouting
import com.mrdip.route.postRouting
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*

fun Application.configureRouting() {
    routing {
        authRouting()
        followsRouting()
        postRouting()
        static {
            resources("static")
        }
    }
}
