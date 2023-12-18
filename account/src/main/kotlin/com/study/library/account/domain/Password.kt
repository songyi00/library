package com.study.library.account.domain

import com.study.library.common.encrypt.sha256Encrypt

data class Password(
    private val text: String
) {
    val encryptValue: String by lazy {
        sha256Encrypt(text)
    }
}
