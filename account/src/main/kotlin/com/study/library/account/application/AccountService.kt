package com.study.library.account.application

import com.study.library.account.domain.Account
import com.study.library.account.port.`in`.AuthenticateUseCase
import com.study.library.account.port.`in`.AuthenticateUseCase.AuthenticationData
import com.study.library.account.port.`in`.SignUpUseCase
import com.study.library.account.port.`in`.SignUpUseCase.AccountData
import com.study.library.account.port.out.AccountCommandPort
import com.study.library.account.port.out.AccountQueryPort
import com.study.library.common.auth.JwtToken
import com.study.library.common.auth.JwtTokenManager
import com.study.library.common.error.AccountNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AccountService(
    private val accountCommandPort: AccountCommandPort,
    private val accountQueryPort: AccountQueryPort,
    private val tokenManager: JwtTokenManager
) : SignUpUseCase, AuthenticateUseCase {

    override suspend fun signUp(accountData: AccountData): Account {
        val account = Account.create(
            email = accountData.email,
            password = accountData.password,
            name = accountData.name
        )

        return accountCommandPort.create(account)
    }

    override suspend fun authenticate(authenticationData: AuthenticationData): JwtToken {
        val account = accountQueryPort.findByEmail(authenticationData.email)
            ?: throw AccountNotFoundException("not found account")
        account.authenticate(authenticationData.password)

        return tokenManager.createAccessToken(authenticationData.email)
    }
}
