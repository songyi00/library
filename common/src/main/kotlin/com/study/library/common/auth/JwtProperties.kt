package com.study.library.common.auth

data class JwtProperties(
    val secretKey: String,
    val expirationInMilliseconds: Long = 1000 * 60 * 60 * 6
)
