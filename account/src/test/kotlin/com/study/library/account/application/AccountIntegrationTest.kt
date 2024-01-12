package com.study.library.account.application

import com.study.library.common.annotation.IntegrationTest
import com.study.library.account.adapter.out.persistence.repository.AccountRepository
import com.study.library.account.fixture.EMAIL
import com.study.library.account.fixture.PASSWORD
import com.study.library.account.fixture.createAccountEntity
import com.study.library.account.port.`in`.AuthenticateUseCase.AuthenticationData
import com.study.library.common.error.AccountNotFoundException
import com.study.library.common.error.UnAuthenticateException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldNotBe

@IntegrationTest
class AccountIntegrationTest(
    private val accountService: AccountService,
    private val accountRepository: AccountRepository
) : FunSpec({

    afterTest {
        accountRepository.deleteAll()
    }

    context("로그인") {
        test("정상적인 사용자인 경우 토큰을 발행한다.") {
            // given
            accountRepository.save(createAccountEntity(email = EMAIL, password = PASSWORD))
            // when
            val actual = accountService.authenticate(AuthenticationData(EMAIL, PASSWORD))

            // then
            actual.token shouldNotBe ""
        }

        test("이메일이 잘못된 경우 예외가 발생한다.") {
            // given
            val account = accountRepository.save(createAccountEntity(email = "ss@ss.com"))

            // when, then
            shouldThrow<AccountNotFoundException> {
                accountService.authenticate(AuthenticationData("abc", account.password))
            }
        }

        test("비밀번호가 잘못된 경우 예외가 발생한다.") {
            // given
            val account = accountRepository.save(createAccountEntity(password = "aa"))

            // when, then
            shouldThrow<UnAuthenticateException> {
                accountService.authenticate(AuthenticationData(account.email, "bb"))
            }
        }
    }
})
