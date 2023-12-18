package com.study.library.account.adapter.out.persistence.entity

import com.study.library.account.domain.Account
import com.study.library.account.domain.Password
import org.springframework.data.relational.core.mapping.Table

@Table("account")
class AccountEntity(
    val id: Long = 0L,
    val email: String,
    val password: String,
    val name: String
) {
    fun toDomain(): Account {
        return Account(
            id = id,
            email = email,
            password = Password(password),
            name = name
        )
    }

    companion object {
        fun from(account: Account): AccountEntity {
            return AccountEntity(
                id = account.id,
                email = account.email,
                password = account.password.encryptValue,
                name = account.name
            )
        }
    }
}
