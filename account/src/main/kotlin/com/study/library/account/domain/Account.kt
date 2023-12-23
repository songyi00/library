package com.study.library.account.domain

import com.study.library.common.encrypt.sha256Encrypt
import com.study.library.common.error.UnAuthenticateException
import org.springframework.data.relational.core.mapping.Table

@Table("account")
class Account(
    val id: Long = 0L,
    val email: String,
    val password: String,
    val name: String
) {
    fun authenticate(password: String) {
        if (this.password != sha256Encrypt(password)) {
            throw UnAuthenticateException("사용자 정보가 일치하지 않습니다.")
        }
    }

    companion object {
        fun create(email: String, password: String, name: String): Account {
            return Account(
                id = 0L,
                email = email,
                password = sha256Encrypt(password),
                name = name
            )
        }
    }
}
