package com.example.plugins

import com.example.ServerInfo
import com.example.auth.authRoutes
import com.example.auth.models.JwtConfig
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(serverInfo: ServerInfo, jwtConfig: JwtConfig) {
    routing {
        get("/") {
            call.respondText("Listening on port ${serverInfo.port}")
        }
        authRoutes(jwtConfig = jwtConfig)
    }
}
