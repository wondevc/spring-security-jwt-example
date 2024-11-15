package com.example.infrastructure.configuration.security.jwt

import com.example.utils.JwtUtils
import io.jsonwebtoken.io.Decoders
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JwtConfiguration {
    @Bean
    fun jwtUtils(properties: JwtProperties): JwtUtils = JwtUtils(
        secret = properties.secret,
        decoder = Decoders.BASE64URL
    )
}
