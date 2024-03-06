package com.study.library.gateway.global

import com.study.library.core.auth.auth.JwtTokenManager
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(JwtTokenManager::class)
class GlobalConfiguration