package com.study.library.gateway

import com.study.library.common.auth.JwtTokenManager
import com.study.library.common.error.ForbiddenException
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange

@Component
class AuthorizationGatewayFilterFactory(
    private val tokenManager: JwtTokenManager
) : AbstractGatewayFilterFactory<AuthorizationGatewayFilterFactory.Config>(Config::class.java) {
    override fun apply(config: Config): GatewayFilter = GatewayFilter { exchange, chain ->

        val token = extractTokenString(exchange)
        val email = tokenManager.getUserEmail(token)
        exchange.attributes[USER_DETAILS] = email

        chain.filter(exchange)
    }


    private fun extractTokenString(exchange: ServerWebExchange): String {
        return runCatching {
            val authorization = exchange.request.headers.getValue(AUTHORIZATION_HEADER)[0]
            authorization.split(BEARER)[1].trim()
        }.onFailure {
            throw ForbiddenException("사용자 토큰 정보를 확인할 수 없습니다.")
        }.getOrThrow()
    }

    object Config

    companion object {
        const val AUTHORIZATION_HEADER = "Authorization"
        const val BEARER = "Bearer"
        const val USER_DETAILS = "userEmail"
    }
}
