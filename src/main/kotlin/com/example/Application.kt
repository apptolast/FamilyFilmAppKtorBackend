package com.example

import com.example.plugins.*
import getJwtConfig
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    val serverInfo = getServerInfo()
    val jwtConfig = getJwtConfig()
    configureSerialization()
    configureSecurity(jwtConfig = jwtConfig)
    configureRouting(serverInfo = serverInfo, jwtConfig = jwtConfig)
}
