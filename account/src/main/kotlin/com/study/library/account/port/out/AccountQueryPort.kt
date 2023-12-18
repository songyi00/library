package com.study.library.account.port.out

import com.study.library.account.domain.Account

interface AccountQueryPort {
    suspend fun loadAccount(id: Long): Account
    suspend fun findByEmail(email: String): Account?
}
