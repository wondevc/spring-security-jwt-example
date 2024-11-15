package com.example.infrastructure.configuration.security

import org.springframework.http.HttpMethod
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher

interface SpringSecurityServerWebExchangeMatcher {
    fun getPermitAllMatchers(): List<ServerWebExchangeMatcher>
    fun getDenyAllMatchers(): List<ServerWebExchangeMatcher>
}

abstract class AbstractSpringSecurityServerWebExchangeMatcher : SpringSecurityServerWebExchangeMatcher {
    override fun getPermitAllMatchers(): List<ServerWebExchangeMatcher> = emptyList()
    override fun getDenyAllMatchers(): List<ServerWebExchangeMatcher> = emptyList()
}

class DefaultSpringSecurityServerWebExchangeMatcher : AbstractSpringSecurityServerWebExchangeMatcher() {
    override fun getPermitAllMatchers(): List<ServerWebExchangeMatcher> {
        return listOf(
            PathPatternParserServerWebExchangeMatcher("/favicon.ico", HttpMethod.GET),
            PathPatternParserServerWebExchangeMatcher("/error"),
        )
    }
}

class ActuatorSpringSecurityServerWebExchangeMatcher : AbstractSpringSecurityServerWebExchangeMatcher() {
    override fun getPermitAllMatchers(): List<ServerWebExchangeMatcher> {
        return listOf(
            PathPatternParserServerWebExchangeMatcher("/actuator/**"),
        )
    }
}
