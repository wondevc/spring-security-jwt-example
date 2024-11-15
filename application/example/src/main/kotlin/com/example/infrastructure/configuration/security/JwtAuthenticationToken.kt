package com.example.infrastructure.configuration.security

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class JwtAuthenticationToken : AbstractAuthenticationToken {
    private val principal: Any
    private val credentials: Any
    private val token: String
    private val name: String

    fun getUsername(): String = this.name

    override fun getCredentials(): Any = this.credentials

    override fun getPrincipal(): Any = this.principal

    constructor(
        token: String,
        name: String,
    ) : super(null) {
        this.token = token
        this.principal = token
        this.credentials = token
        this.name = name
    }

    constructor(
        token: String,
        name: String,
        authorities: Collection<GrantedAuthority>,
    ) : super(authorities) {
        this.isAuthenticated = true

        this.token = token
        this.principal = token
        this.credentials = token
        this.name = name
    }
}
