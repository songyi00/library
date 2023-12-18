package com.study.library.common.auth

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.*


@Component
@ConfigurationPropertiesScan
class JwtTokenProvider(
    private val jwtProperties: JwtProperties
) {
    fun createAccessToken(email: String): JwtToken {
        val now = Date()
        val expirationTime = now.time + jwtProperties.expirationInMilliseconds
        val secretKey = Keys.hmacShaKeyFor(
            jwtProperties.secretKey.toByteArray(StandardCharsets.UTF_8)
        )

        return JwtToken(
            Jwts.builder()
                .claim("email", email)
                .setIssuedAt(now)
                .setExpiration(Date(expirationTime))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact()
        )
    }
}