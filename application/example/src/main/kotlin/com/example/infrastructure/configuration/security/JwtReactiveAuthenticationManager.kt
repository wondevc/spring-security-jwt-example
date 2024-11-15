package com.example.infrastructure.configuration.security

import com.example.utils.JwtUtils
import kotlinx.coroutines.reactor.mono
import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.core.Authentication
import reactor.core.publisher.Mono

class JwtReactiveAuthenticationManager(jwtUtils: JwtUtils) : ReactiveAuthenticationManager {
    private val jwtAuthenticationConverter: Converter<String, AbstractAuthenticationToken> =
        JwtAuthenticationConverter(jwtUtils)

    override fun authenticate(authentication: Authentication): Mono<Authentication> = mono {
        authentication.takeIf { it is BearerTokenAuthenticationToken }
            ?.let { it as BearerTokenAuthenticationToken }
            ?.let(BearerTokenAuthenticationToken::getToken)
            ?.let(jwtAuthenticationConverter::convert)
    }
}
