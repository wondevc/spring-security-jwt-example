package com.example.utils

import com.example.model.support.Expiration
import com.example.model.domain.IJwtUser
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoder
import io.jsonwebtoken.security.Keys
import java.time.OffsetDateTime
import java.util.*

class JwtUtils(
    secret: String,
    decoder: Decoder<CharSequence, ByteArray>,
) {
    private val secretKey = Keys.hmacShaKeyFor(decoder.decode(secret))

    fun generateToken(
        jwtUser: IJwtUser,
        fixedOffsetDateTime: OffsetDateTime,
        expiration: Expiration,
    ): String {
        val fixedDate = Date.from(fixedOffsetDateTime.toInstant())

        val expirationOffsetDateTime = fixedOffsetDateTime.plus(
            expiration.time,
            expiration.unit
        )
        val expirationDate = Date.from(expirationOffsetDateTime.toInstant())

        return Jwts.builder()
            .subject(jwtUser.getUsername())
            .expiration(expirationDate)
            .notBefore(fixedDate)
            .issuedAt(fixedDate)
            .id(UUIDGenerator.newV7UUID().toString())
            .signWith(secretKey)
            .compact()
    }

    fun extractUsername(jwtToken: String): String {
        return extractClaim(
            jwtToken,
            Claims::getSubject
        )
    }

    private fun <T> extractClaim(
        jwtToken: String,
        claimsResolver: Function1<Claims, T>,
    ): T {
        val claims = extractAllClaims(jwtToken)
        return claimsResolver.invoke(claims)
    }

    private fun extractAllClaims(jwtToken: String): Claims {
        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(jwtToken)
            .payload
    }
}
