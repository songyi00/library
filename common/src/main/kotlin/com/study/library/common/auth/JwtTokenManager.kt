package com.study.library.common.auth

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*
import javax.crypto.SecretKey

class JwtTokenManager(
    @Value("\${jwt.secret-key}") val secretKey: String,
    @Value("\${jwt.expiration-in-milliseconds}") val expirationInMilliseconds: Long,
) {

    private val logger = KotlinLogging.logger {}
    private val secretKeyEncoded: SecretKey = Keys.hmacShaKeyFor(secretKey.toByteArray())

    fun createAccessToken(email: String): JwtToken {
        val now = Date()
        val expirationTime = now.time + expirationInMilliseconds
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