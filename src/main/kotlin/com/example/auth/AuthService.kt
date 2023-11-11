package com.example.auth

import com.example.auth.models.JwtConfig

import com.example.auth.models.User
import com.example.auth.models.LoginRequest
import com.example.auth.models.RegisterRequest
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.Date

class AuthService(private val jwtConfig: JwtConfig) {

    // Simula una base de datos en memoria
    private val users = mutableListOf<User>()

    fun authenticate(loginRequest: LoginRequest): User? {
        val user = users.find { it.username == loginRequest.username }
        // Aquí deberías comparar la contraseña hasheada
        if (user != null && user.passwordHash == hashPassword(loginRequest.password)) {
            return user
        }
        return null
    }

    fun register(registerRequest: RegisterRequest): Boolean {
        if (users.any { it.username == registerRequest.username }) {
            return false // El usuario ya existe
        }
        // Aquí deberías hashear la contraseña antes de almacenarla
        val newUser = User(
            id = users.size + 1, // Esto es solo un ejemplo, deberías tener un ID único adecuado
            username = registerRequest.username,
            email = registerRequest.email,
            passwordHash = hashPassword(registerRequest.password)
        )
        users.add(newUser)
        return true
    }

    fun generateToken(user: User): String {
        return JWT.create()
            .withAudience(jwtConfig.audience)
            .withIssuer(jwtConfig.issuer)
            .withClaim("username", user.username)
            .withExpiresAt(Date(System.currentTimeMillis() + 3600000)) // 1 hora de validez
            .sign(Algorithm.HMAC256(jwtConfig.secret))
    }

    private fun hashPassword(password: String): String {
        // Aquí deberías implementar un algoritmo de hasheo seguro
        return password.reversed() // Esto es solo un ejemplo y no debe usarse en producción
    }
}
