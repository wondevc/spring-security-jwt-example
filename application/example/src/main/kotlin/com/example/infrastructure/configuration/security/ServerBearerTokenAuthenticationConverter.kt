package com.example.infrastructure.configuration.security

import kotlinx.coroutines.reactor.mono
import org.springframework.http.HttpHeaders
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

class ServerBearerTokenAuthenticationConverter : ServerAuthenticationConverter {
    private val bearerTokenHeaderName = HttpHeaders.AUTHORIZATION

    override fun convert(exchange: ServerWebExchange): Mono<Authentication> = mono {
        token(exchange.request)?.let { BearerTokenAuthenticationToken(it) }
    }

    private fun token(request: ServerHttpRequest): String? {
        return resolveFromAuthorizationHeader(request.headers)
            .takeUnless { it.isNullOrEmpty() }
    }

    private fun resolveFromAuthorizationHeader(headers: HttpHeaders): String? {
        val authorization = headers.getFirst(bearerTokenHeaderName)

        if (authorization.isNullOrEmpty()) {
            return null
        }

        return when (authorization.startsWith(BEARER_PREFIX)) {
            true -> authorization.removePrefix(BEARER_PREFIX).trim()
            false -> null
        }
    }

    companion object {
        private const val BEARER_PREFIX = "Bearer"
    }
}
