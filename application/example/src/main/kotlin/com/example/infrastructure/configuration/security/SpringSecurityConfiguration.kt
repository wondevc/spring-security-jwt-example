package com.example.infrastructure.configuration.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository
import org.springframework.web.cors.reactive.CorsConfigurationSource

@Configuration
@EnableWebFluxSecurity
class SpringSecurityConfiguration {
    @Bean
    fun securityWebFilterChain(
        http: ServerHttpSecurity,
        corsConfiguration: CorsConfigurationSource,
        exchangeMatchers: List<SpringSecurityServerWebExchangeMatcher>,
        jwtAuthenticationWebFilter: AuthenticationWebFilter,
    ): SecurityWebFilterChain = http
        .headers { headers -> headers
            .frameOptions {
                it.disable()
            }
        }
        .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
        .csrf { it.disable() }
        .cors { it.configurationSource(corsConfiguration) }
        .httpBasic { it.disable() }
        .formLogin { it.disable() }
        .logout { it.disable() }
        .authorizeExchange { exchange -> exchange
            .apply {
                exchangeMatchers
                    .flatMap { it.getPermitAllMatchers() }
                    .takeUnless { it.isEmpty() }
                    ?.let { matchers(*it.toTypedArray()).permitAll() }

                exchangeMatchers
                    .flatMap { it.getDenyAllMatchers() }
                    .takeUnless { it.isEmpty() }
                    ?.let { matchers(*it.toTypedArray()).denyAll() }

                anyExchange().authenticated()
            }
        }
        .addFilterAt(jwtAuthenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
        .build()
}
