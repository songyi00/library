package com.study.library.account.port.`in`

import com.study.library.common.auth.JwtToken
import jakarta.validation.constraints.Email

interface AuthenticateUseCase {
    suspend fun authenticate(authenticationData: AuthenticationData): JwtToken

    data class AuthenticationData(
        @Email
        val email: String,
        val password: String
    )
}
