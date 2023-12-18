package com.study.library.account.adapter.out.persistence

import com.study.library.account.adapter.out.persistence.repository.AccountRepository
import com.study.library.account.adapter.out.persistence.repository.getById
import com.study.library.account.domain.Account
import com.study.library.account.port.out.AccountQueryPort
import org.springframework.stereotype.Component

@Component
class AccountQueryAdapter(
    private val accountRepository: AccountRepository
) : AccountQueryPort {
    override suspend fun loadAccount(id: Long): Account {

        return accountRepository.getById(id)
            .toDomain()
    }

    override suspend fun findByEmail(email: String): Account? {
        val accountEntity = accountRepository.findByEmail(email)

        return accountEntity?.toDomain()
    }
}
