package com.study.library.gateway

import com.study.library.core.auth.auth.JwtTokenManager
import com.study.library.gateway.fixture.EMAIL
import com.study.library.gateway.fixture.EXPIRATION_TIME_MS
import com.study.library.gateway.fixture.SECRET_KEY
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class JwtTokenManagerTest : FunSpec({
    val tokenManager: JwtTokenManager by lazy {
        JwtTokenManager(SECRET_KEY, EXPIRATION_TIME_MS)
    }
    test("토큰을 생성하고 정보를 추출할 수 있다.") {
        // given
        val email = EMAIL

        // when
        val actual = tokenManager.createAccessToken(email)

        // then
        tokenManager.getUserEmail(actual.token) shouldBe email
    }


    test("토큰을 생성하고 검증할 수 있다.") {
        // given
        val email = EMAIL
        val token = tokenManager.createAccessToken(email)

        // when
        val actual = tokenManager.isValidToken(token.token)

        // then
        actual shouldBe true
    }

    test("정상적이지 않은 토큰의 경우 예외가 발생한다.") {
        // given
        val token = "12345"

        // when
        val actual = tokenManager.isValidToken(token)

        // then
        actual shouldBe false
    }

    test("정상 토큰으로부터 사용자 정보를 추출할 수 있다.") {
        // given
        val email = EMAIL
        val token = tokenManager.createAccessToken(email)

        // when
        val actual = tokenManager.getUserEmail(token.token)

        // then
        actual shouldBe email
    }
})
