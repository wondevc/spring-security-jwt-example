package com.example.infrastructure.configuration.security

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.server.ServerAuthenticationEntryPoint
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

class SpringSecurityServerAuthenticationEntryPoint : ServerAuthenticationEntryPoint {
    override fun commence(exchange: ServerWebExchange, ex: AuthenticationException): Mono<Void> {
        return Mono.fromRunnable {
            exchange.response.apply {
                statusCode = HttpStatus.UNAUTHORIZED
                headers.contentType = MediaType.APPLICATION_JSON

                ex.message?.toByteArray()?.let { bufferFactory().wrap(it) }
            }
        }
    }
}
