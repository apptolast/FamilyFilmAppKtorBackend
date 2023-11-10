package com.example.auth

import com.example.auth.models.User


class AuthService {

    fun authenticate(ussername: String, password: String): User? {
        return null
    }

    fun register(user: User): Boolean {
        return true
    }
}