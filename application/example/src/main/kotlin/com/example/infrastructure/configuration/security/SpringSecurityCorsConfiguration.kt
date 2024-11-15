package com.example.infrastructure.configuration.security

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.http.HttpMethod
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsConfigurationSource
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource

@Configuration
class SpringSecurityCorsConfiguration {
    @Bean
    fun corsConfiguration(originConstants: OriginConstants): CorsConfigurationSource =
        UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration(
                "/**",
                CorsConfiguration().apply {
                    val possibleAllowCredentials = when (originConstants) {
                        is DefaultOriginConstants -> false
                        else -> {
                            originConstants.getAll().firstOrNull {
                                it == IMPOSSIBLE_ALLOW_CREDENTIALS_ORIGIN
                            } == null
                        }
                    }

                    allowedOrigins = originConstants.getAll()
                    allowedHeaders = listOf("*")
                    allowedMethods = listOf(
                        HttpMethod.DELETE,
                        HttpMethod.GET,
                        HttpMethod.POST,
                        HttpMethod.PUT,
                        HttpMethod.PATCH,
                        HttpMethod.OPTIONS
                    ).map { it.name() }
                    allowCredentials = possibleAllowCredentials
                    maxAge = 3600
                }
            )
        }

    @Profile("local")
    @Bean
    fun localOriginConstants(): LocalOriginConstants = LocalOriginConstants

    @ConditionalOnMissingBean(OriginConstants::class)
    @Bean
    fun defaultOriginConstants(): OriginConstants = DefaultOriginConstants

    companion object {
        private const val IMPOSSIBLE_ALLOW_CREDENTIALS_ORIGIN = "*"
    }
}
