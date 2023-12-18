package com.study.library.account.port.out

import com.study.library.account.domain.Account

interface AccountCommandPort {
    suspend fun create(account: Account): Account
}
