package com.example.infrastructure.configuration.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class SpringSecurityAuthorizationConfiguration {
    @Bean
    fun defaultSpringSecurityServerWebExchangeMatcher(): SpringSecurityServerWebExchangeMatcher =
        DefaultSpringSecurityServerWebExchangeMatcher()

    @Profile("local")
    @Bean
    fun actuatorSpringSecurityServerWebExchangeMatcher(): SpringSecurityServerWebExchangeMatcher =
        ActuatorSpringSecurityServerWebExchangeMatcher()
}
