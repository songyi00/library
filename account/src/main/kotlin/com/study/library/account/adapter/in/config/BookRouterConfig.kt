package com.study.library.account.adapter.`in`.config

import com.study.library.account.adapter.`in`.AccountRouter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class AccountRouterConfig(
    private val accountRouter: AccountRouter
) {
    @Bean
    fun routes(): RouterFunction<*> = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            POST("/api/signUp", accountRouter::signUp)
            POST("/api/signIn", accountRouter::authenticate)
        }
    }
}
