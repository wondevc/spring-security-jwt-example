package com.example.infrastructure.configuration.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository

@Configuration
@EnableWebFluxSecurity
class SpringSecurityConfiguration {
    @Bean
    fun securityWebFilterChain(
        http: ServerHttpSecurity,
    ): SecurityWebFilterChain = http
        .headers { headers -> headers
            .frameOptions {
                it.disable()
            }
        }
        .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
        .csrf { it.disable() }
        .cors { it.disable() }
        .httpBasic { it.disable() }
        .formLogin { it.disable() }
        .logout { it.disable() }
        .authorizeExchange {
            it.anyExchange().permitAll()
        }
        .build()
}