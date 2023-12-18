package com.study.library.account.adapter.out.persistence

import com.study.library.account.adapter.out.persistence.entity.AccountEntity
import com.study.library.account.domain.Account
import com.study.library.account.port.out.AccountCommandPort
import com.study.library.account.adapter.out.persistence.repository.AccountRepository
import org.springframework.stereotype.Component

@Component
class AccountCommandAdapter(
    private val accountRepository: AccountRepository
) : AccountCommandPort {
    override suspend fun create(account: Account): Account {

        return accountRepository.save(AccountEntity.from(account))
            .toDomain()
    }
}
