package com.study.library.core.auth.auth

import com.study.library.core.auth.error.NotFoundException
import org.springframework.web.reactive.function.server.ServerRequest
import kotlin.jvm.optionals.getOrElse

const val AUTHORIZATION_HEADER = "Authorization"
const val BEARER = "Bearer"
const val USER_DETAILS = "userEmail"
fun ServerRequest.getUserEmail(): String {
    return this.attribute(USER_DETAILS).getOrElse {
        throw NotFoundException("사용자 정보를 확인할 수 없습니다.")
    }.toString()
}