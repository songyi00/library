package com.study.library.book.application

import com.study.library.book.fixture.createBook
import com.study.library.book.book.application.BookService
import com.study.library.book.book.port.`in`.RegisterBookUseCase
import com.study.library.book.book.port.out.BookCommandPort
import com.study.library.book.book.port.out.BookQueryPort
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk

class BookServiceTest : BehaviorSpec({
    val bookCommandPort = mockk<BookCommandPort>()
    val bookQueryPort = mockk<BookQueryPort>()
    val bookService = BookService(bookCommandPort, bookQueryPort)

    Given("정상적인 도서 데이터인 경우") {
        val book = createBook()
        coEvery { bookCommandPort.save(any()) } returns book

        val request = RegisterBookUseCase.BookData(
            name = book.name,
            author = book.author,
            isbn = book.isbn.value,
            source = book.source,
            publishedDate = book.publishedDate,
            totalStock = book.totalStock
        )

        When("도서 등록 요청시") {
            val actual = bookService.registerBook(request)
            Then("올바르게 등록된다") {
                actual.name shouldBe book.name
                actual.author shouldBe book.author
                actual.source shouldBe book.source
                actual.bookStatus shouldBe book.bookStatus
                actual.currentStock shouldBe book.currentStock
                actual.totalStock shouldBe book.totalStock
            }
        }
    }

    afterTest {
        clearAllMocks()
    }
})
