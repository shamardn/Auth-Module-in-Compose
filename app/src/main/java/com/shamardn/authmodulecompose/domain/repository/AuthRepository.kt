package com.shamardn.authmodulecompose.domain.repository

interface AuthRepository {
    suspend fun login(email: String, password: String): Boolean
    suspend fun register(email: String, password: String): Boolean
}