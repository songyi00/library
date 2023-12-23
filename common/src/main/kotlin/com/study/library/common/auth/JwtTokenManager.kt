package com.study.library.common.auth

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import mu.KotlinLogging
import java.util.*
import javax.crypto.SecretKey

class JwtTokenManager(
    private val jwtProperties: JwtProperties
) {

    private val logger = KotlinLogging.logger {}
    private val secretKeyEncoded: SecretKey = Keys.hmacShaKeyFor(jwtProperties.secretKey.toByteArray())

    fun createAccessToken(email: String): JwtToken {
        val now = Date()
        val expirationTime = now.time + jwtProperties.expirationInMilliseconds
        val claims = Jwts.claims().setSubject(email)

        return JwtToken(
            Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(Date(expirationTime))
                .signWith(secretKeyEncoded)
                .compact()
        )
    }

    fun getUserEmail(token: String): String {
        return getClaims(token)
            .body
            .subject
    }

    fun isValidToken(token: String): Boolean {
        return runCatching {
            getClaims(token)
            true
        }.getOrElse {
            logger.error { "잘못된 토큰 정보입니다. [token: $token]" }
            false
        }
    }

    private fun getClaims(token: String): Jws<Claims> {
        return Jwts.parserBuilder()
            .setSigningKey(secretKeyEncoded)
            .build()
            .parseClaimsJws(token)
    }
}