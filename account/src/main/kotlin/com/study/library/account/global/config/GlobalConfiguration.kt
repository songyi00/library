package com.study.library.account.global.config

import com.study.library.common.auth.JwtProperties
import com.study.library.common.auth.JwtTokenManager
import com.study.library.common.error.GlobalExceptionHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.codec.ServerCodecConfigurer

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
