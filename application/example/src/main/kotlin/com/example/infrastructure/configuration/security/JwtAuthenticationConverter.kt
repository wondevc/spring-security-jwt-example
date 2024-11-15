package com.example.infrastructure.configuration.security

import com.example.utils.JwtUtils
import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken

class JwtAuthenticationConverter(
    private val jwtUtils: JwtUtils,
) : Converter<String, AbstractAuthenticationToken> {
    override fun convert(source: String): AbstractAuthenticationToken {
        val username = jwtUtils.extractUsername(source)

        return JwtAuthenticationToken(
            token = source,
            name = username,
            authorities = emptyList(),
        )
    }
}
