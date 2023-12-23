package com.study.library.account.fixture

import com.study.library.account.adapter.out.persistence.entity.AccountEntity
import com.study.library.account.domain.Account
import com.study.library.common.encrypt.sha256Encrypt

const val EMAIL = "test@test.com"
const val PASSWORD = "abc"
const val NAME = "test"
fun createAccount(
    email: String = EMAIL,
    password: String = PASSWORD,
    name: String = NAME
): Account {
    return Account.create(
        email = email,
        password = password,
        name = name
    )
}

fun createAccountEntity(
    email: String = EMAIL,
    password: String = PASSWORD,
    name: String = NAME
): AccountEntity {
    return AccountEntity(
        email = email,
        password = sha256Encrypt(password),
        name = name
    )
}
