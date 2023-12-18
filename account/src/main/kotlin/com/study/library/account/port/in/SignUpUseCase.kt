package com.study.library.account.port.`in`

import com.study.library.account.domain.Account
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size

interface SignUpUseCase {
    suspend fun signUp(accountData: AccountData): Account
    data class AccountData(
        @Email
        val email: String,
        @Size(min = 10, max = 50)
        val password: String,
        val name: String
    )
}
