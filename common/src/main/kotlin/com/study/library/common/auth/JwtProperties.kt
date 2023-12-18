package com.study.library.common.auth

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(value = "jwt")
class JwtProperties(
    val secretKey: String,
    val expirationInMilliseconds: Long = 1000 * 60 * 60 * 6
)