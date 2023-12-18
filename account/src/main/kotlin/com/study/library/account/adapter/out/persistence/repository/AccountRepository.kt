package com.study.library.account.adapter.out.persistence.repository

import com.study.library.account.adapter.out.persistence.entity.AccountEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

suspend fun AccountRepository.getById(id: Long): AccountEntity {
    return findById(id) ?: throw IllegalArgumentException("not found account")
}

interface AccountRepository : CoroutineCrudRepository<AccountEntity, Long> {
    suspend fun findByEmail(email: String): AccountEntity?
}
