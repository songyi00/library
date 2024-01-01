package com.study.library.account.global.config

import com.study.library.common.auth.JwtTokenManager
import com.study.library.common.error.GlobalExceptionHandler
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.http.codec.ServerCodecConfigurer

@Configuration
@Import(JwtTokenManager::class)
class GlobalConfiguration {

    @Bean
    fun exceptionHandler(
        errorAttributes: ErrorAttributes,
        applicationContext: ApplicationContext,
        serverCodecConfigurer: ServerCodecConfigurer
    ): GlobalExceptionHandler {
        return GlobalExceptionHandler(errorAttributes, applicationContext, serverCodecConfigurer)
    }
}
