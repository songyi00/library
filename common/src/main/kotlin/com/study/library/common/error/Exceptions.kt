package com.study.library.common.error


open class NotFoundException(override val message: String?) : RuntimeException()
class AccountNotFoundException(override val message: String) : NotFoundException(message)
class UnAuthenticateException(override val message: String) : RuntimeException()