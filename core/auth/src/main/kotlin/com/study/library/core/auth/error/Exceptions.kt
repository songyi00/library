package com.study.library.core.auth.error


open class NotFoundException(override val message: String?) : RuntimeException()
class ForbiddenException(override val message: String?) : RuntimeException()
class AccountNotFoundException(override val message: String) : NotFoundException(message)
class UnAuthenticateException(override val message: String) : RuntimeException()
