package com.study.library.book.adapter.`in`.config

import com.study.library.book.adapter.`in`.BookRouter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class BookRouterConfig(
    private val bookRouter: BookRouter
) {
    @Bean
    fun routes(): RouterFunction<*> = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            POST("/api/books", bookRouter::registerBook)
            DELETE("/api/books/{bookId}", bookRouter::deleteBook)
        }
    }
}
