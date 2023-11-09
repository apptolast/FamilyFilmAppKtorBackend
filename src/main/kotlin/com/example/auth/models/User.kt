package com.example.auth.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val username: String,
    val email: String,
    val passwordHash: String
)

@Serializable
data class JwTPayload(
    val userID: String,
)
