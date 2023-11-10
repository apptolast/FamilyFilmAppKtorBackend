package com.example.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.auth.models.JwtConfig
import com.example.auth.models.User
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.Date


fun Route.authRoutes(jwtConfig: JwtConfig) {
    post("/login") {
        val user = call.receive<User>()
        val token = JWT.create()
            .withAudience(jwtConfig.audience)
            .withIssuer(jwtConfig.issuer)
            .withClaim("username", user.username)
            .withExpiresAt(Date(System.currentTimeMillis() + 60000))
            .sign(Algorithm.HMAC256(jwtConfig.secret))
        call.respond(hashMapOf("token" to token))
    }

    post("/register") {

    }
}