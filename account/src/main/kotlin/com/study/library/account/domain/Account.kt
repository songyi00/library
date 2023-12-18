package com.study.library.account.domain

import com.study.library.common.error.UnAuthenticateException
import org.springframework.data.relational.core.mapping.Table

@Table("account")
class Account(
    val id: Long = 0L,
    val email: String,
    val password: Password,
    val name: String
) {
    fun authenticate(password: String) {
        if (this.password != Password(password)) {
            throw UnAuthenticateException("사용자 정보가 일치하지 않습니다.")
        }
    }
}
