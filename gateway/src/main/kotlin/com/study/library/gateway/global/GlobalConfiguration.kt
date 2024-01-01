package com.study.library.gateway.global

import com.study.library.common.auth.JwtTokenManager
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(JwtTokenManager::class)
class GlobalConfiguration