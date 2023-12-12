package com.songyi.r2dbctutorial.book.adapter.`in`

import com.songyi.r2dbctutorial.book.port.`in`.RegisterBookUseCase
import org.springframework.stereotype.Controller
import org.springframework.web.reactive.function.server.*
import java.net.URI

@Controller
class BookRouter(
    private val registerBookUseCase: RegisterBookUseCase
) {
    suspend fun registerBook(request: ServerRequest): ServerResponse {
            val bookData = request.awaitBody<RegisterBookUseCase.BookData>()
            val response = registerBookUseCase.registerBook(bookData)

        return ServerResponse.created(URI.create("/api/books/${response.id}"))
            .bodyValueAndAwait(response)
    }
}