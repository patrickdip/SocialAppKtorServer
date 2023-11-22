package com.mrdip.plugins

import com.mrdip.route.authRouting
import com.mrdip.route.followsRouting
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    routing {
        authRouting()
        followsRouting()
    }
}
