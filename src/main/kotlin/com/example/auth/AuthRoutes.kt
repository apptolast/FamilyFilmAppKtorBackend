package com.example.auth

import com.example.auth.models.JwtConfig
import com.example.auth.models.LoginRequest
import com.example.auth.models.RegisterRequest
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.*

fun Application.authRoutes(jwtConfig: JwtConfig) {
    val authService = AuthService(jwtConfig)

    routing {
        post("/login") {
            val loginRequest = call.receive<LoginRequest>()
            val user = authService.authenticate(loginRequest)
            if (user != null) {
                val token = authService.generateToken(user)
                call.respond(hashMapOf("token" to token))
            } else {
                call.respond(HttpStatusCode.Unauthorized, "Invalid credentials")
            }
        }

        post("/register") {
            val registerRequest = call.receive<RegisterRequest>()
            val registered = authService.register(registerRequest)
            if (registered) {
                call.respond(HttpStatusCode.Created, "User created")
            } else {
                call.respond(HttpStatusCode.BadRequest, "User already exists")
            }
        }
    }
}
