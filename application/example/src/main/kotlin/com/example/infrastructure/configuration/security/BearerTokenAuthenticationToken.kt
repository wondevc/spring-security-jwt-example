package com.example.infrastructure.configuration.security

import org.springframework.security.authentication.AbstractAuthenticationToken

class BearerTokenAuthenticationToken(
    private val token: String
) : AbstractAuthenticationToken(emptyList()) {
    fun getToken(): String = this.token

    override fun getCredentials(): Any = this.token
    override fun getPrincipal(): Any = this.token
}
