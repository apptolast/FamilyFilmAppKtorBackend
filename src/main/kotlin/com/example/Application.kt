package com.example

import com.example.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    val port = environment.config.propertyOrNull("ktor.deployment.port")?.getString() ?: "8080"
    configureSerialization()
    configureSecurity()
    configureRouting(port = port)
}
