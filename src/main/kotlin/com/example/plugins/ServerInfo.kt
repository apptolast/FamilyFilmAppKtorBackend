package com.example.plugins

import com.example.ServerInfo
import com.example.auth.models.JwtConfig
import io.ktor.server.application.*

fun Application.getServerInfo(): ServerInfo {
    val port = environment.config.propertyOrNull("ktor.deployment.port")?.getString() ?: "8080"
    return ServerInfo(port = port)
}