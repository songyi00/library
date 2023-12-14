package com.songyi.r2dbctutorial.book.adapter.`in`

import com.songyi.r2dbctutorial.book.port.`in`.DeleteBookUseCase
import com.songyi.r2dbctutorial.book.port.`in`.RegisterBookUseCase
import net.sf.jsqlparser.schema.Server
import org.springframework.stereotype.Controller
import org.springframework.web.reactive.function.server.*
import java.net.URI

@Controller
class BookRouter(
    private val registerBookUseCase: RegisterBookUseCase,
    private val deleteBookUseCase: DeleteBookUseCase
) {
    suspend fun registerBook(request: ServerRequest): ServerResponse {
        val bookData = request.awaitBody<RegisterBookUseCase.BookData>()
        val response = registerBookUseCase.registerBook(bookData)

        return ServerResponse.created(
            URI.create("/api/books/${response.id}")
        ).bodyValueAndAwait(response)
    }

    suspend fun deleteBook(request: ServerRequest): ServerResponse {
        val bookId = request.pathVariable("bookId").toLong()
        deleteBookUseCase.deleteBook(bookId)

        return ServerResponse.noContent().buildAndAwait()
    }
}