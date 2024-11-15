package com.example.infrastructure.configuration.security

import com.example.utils.JwtUtils
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.web.server.ServerAuthenticationEntryPoint
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.security.web.server.authentication.ServerAuthenticationEntryPointFailureHandler

@Configuration
class SpringSecurityAuthenticationConfiguration {
    @Bean
    fun jwtAuthenticationWebFilter(
        jwtReactiveAuthenticationManager: ReactiveAuthenticationManager,
        serverBearerTokenAuthenticationConverter: ServerAuthenticationConverter,
        springSecurityServerAuthenticationEntryPoint: ServerAuthenticationEntryPoint,
    ): AuthenticationWebFilter = AuthenticationWebFilter(jwtReactiveAuthenticationManager)
        .apply {
            setServerAuthenticationConverter(serverBearerTokenAuthenticationConverter)
            setAuthenticationFailureHandler(
                ServerAuthenticationEntryPointFailureHandler(
                    springSecurityServerAuthenticationEntryPoint
                )
            )
        }

    @Bean
    fun jwtReactiveAuthenticationManager(jwtUtils: JwtUtils): ReactiveAuthenticationManager =
        JwtReactiveAuthenticationManager(jwtUtils)

    @Bean
    fun serverBearerTokenAuthenticationConverter(): ServerAuthenticationConverter =
        ServerBearerTokenAuthenticationConverter()

    @Bean
    fun springSecurityServerAuthenticationEntryPoint(): ServerAuthenticationEntryPoint =
        SpringSecurityServerAuthenticationEntryPoint()
}
