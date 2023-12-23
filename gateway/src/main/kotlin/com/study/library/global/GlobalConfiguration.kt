package com.study.library.global

import com.study.library.common.auth.JwtProperties
import com.study.library.common.auth.JwtTokenManager
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GlobalConfiguration(
    @Value("\${jwt.secret-key}") val secretKey: String,
    @Value("\${jwt.expiration-in-milliseconds}") val expirationInMilliseconds: Long,
) {

    @Bean
    fun jwtTokenManager(): JwtTokenManager {
        return JwtTokenManager(
            jwtProperties = JwtProperties(
                secretKey, expirationInMilliseconds
            )
        )
    }
}