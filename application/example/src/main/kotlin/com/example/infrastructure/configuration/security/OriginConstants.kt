package com.example.infrastructure.configuration.security

interface OriginConstants {
    fun getAll(): List<String>
}

object DefaultOriginConstants : OriginConstants {
    override fun getAll(): List<String> = listOf("*")
}

object LocalOriginConstants : OriginConstants {
    override fun getAll(): List<String> = listOf(
        "http://localhost:8080",
    )
}
