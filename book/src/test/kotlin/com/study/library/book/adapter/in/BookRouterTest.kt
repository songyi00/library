package com.study.library.book.adapter.`in`

import com.ninjasquad.springmockk.MockkBean
import com.study.library.book.fixture.createBook
import com.study.library.book.adapter.`in`.BookRouter
import com.study.library.book.adapter.`in`.config.BookRouterConfig
import com.study.library.book.domain.Book
import com.study.library.book.port.`in`.DeleteBookUseCase
import com.study.library.book.port.`in`.RegisterBookUseCase
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

@WebFluxTest
@ContextConfiguration(classes = [BookRouter::class, BookRouterConfig::class])
@MockkBean(RegisterBookUseCase::class, DeleteBookUseCase::class)
class BookRouterTest(
    private val webTestClient: WebTestClient,
    private val registerBookUseCase: RegisterBookUseCase,
    private val deleteBookUseCase: DeleteBookUseCase
) : FunSpec({

    test("정상적인 도서 등록이 가능하다.") {
        val book = createBook()
        coEvery { registerBookUseCase.registerBook(any()) } returns book

        val request = RegisterBookUseCase.BookData(
            name = book.name,
            author = book.author,
            isbn = book.isbn.value,
            source = book.source,
            publishedDate = book.publishedDate,
            totalStock = book.totalStock
        )

        webTestClient.post()
            .uri("/api/books")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .exchange()
            .expectStatus().isCreated
            .expectBody<Book>()
            .consumeWith {
                val res: Book = it.responseBody!!
                res.name shouldBe book.name
                res.currentStock shouldBe book.totalStock
                res.totalStock shouldBe book.totalStock
            }
    }

    test("도서 삭제가 가능하다.") {
        coEvery { deleteBookUseCase.deleteBook(any()) } returns Unit

        webTestClient.delete()
            .uri("/api/books/1")
            .exchange()
            .expectStatus().isNoContent
    }
})
