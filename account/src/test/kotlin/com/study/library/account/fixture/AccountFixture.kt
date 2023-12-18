package com.study.library.account.fixture

import com.study.library.account.adapter.out.persistence.entity.AccountEntity
import com.study.library.account.domain.Account
import com.study.library.account.domain.Password

const val EMAIL = "test@test.com"
val PASSWORD = Password("abc")
const val NAME = "test"
fun createAccount(
    email: String = EMAIL,
    password: Password = PASSWORD,
    name: String = NAME
): Account {
    return Account(
        email = email,
        password = password,
        name = name
    )
}

fun createAccountEntity(
    email: String = EMAIL,
    password: Password = PASSWORD,
    name: String = NAME
): AccountEntity {
    return AccountEntity(
        email = email,
        password = password.encryptValue,
        name = name
    )
}
