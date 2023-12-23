package com.study.library.account.adapter.`in`

import com.study.library.account.port.`in`.AuthenticateUseCase
import com.study.library.account.port.`in`.SignUpUseCase
import org.springframework.stereotype.Controller
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import java.net.URI

@Controller
class AccountRouter(
    private val signUpUseCase: SignUpUseCase,
    private val authenticateUseCase: AuthenticateUseCase
) {
    suspend fun signUp(request: ServerRequest): ServerResponse {
        val accountData = request.awaitBody<SignUpUseCase.AccountData>()
        val response = signUpUseCase.signUp(accountData)

        return ServerResponse.created(
            URI.create("/api/signUp/${response.id}")
        ).bodyValueAndAwait(response)
    }

    suspend fun authenticate(request: ServerRequest): ServerResponse {
        val authenticationData = request.awaitBody<AuthenticateUseCase.AuthenticationData>()
        val response = authenticateUseCase.authenticate(authenticationData)

        return ServerResponse.ok().bodyValueAndAwait(response)
    }
}
