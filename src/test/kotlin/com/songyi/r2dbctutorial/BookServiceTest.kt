package com.songyi.r2dbctutorial

import com.songyi.r2dbctutorial.book.application.BookService
import com.songyi.r2dbctutorial.book.domain.Book
import com.songyi.r2dbctutorial.book.domain.BookStatus
import com.songyi.r2dbctutorial.book.domain.Source
import com.songyi.r2dbctutorial.book.domain.vo.ISBN
import com.songyi.r2dbctutorial.book.port.`in`.RegisterBookUseCase.BookData
import com.songyi.r2dbctutorial.book.port.`in`.RegisterBookUseCase
import com.songyi.r2dbctutorial.book.port.out.BookCommandPort
import com.songyi.r2dbctutorial.book.port.out.BookQueryPort
import com.songyi.r2dbctutorial.config.IntegrationTest
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldHave
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import java.time.LocalDateTime

class BookServiceTest : BehaviorSpec({
    val bookCommandPort = mockk<BookCommandPort>()
    val bookQueryPort = mockk<BookQueryPort>()
    val bookService = BookService(bookCommandPort, bookQueryPort)

    Given("정상적인 도서 데이터인 경우") {
        coEvery { bookCommandPort.save(any()) } returns Book(
            name = "책이름",
            author = "저자",
            isbn = ISBN("9788994327808"),
            source = Source.PURCHASED,
            publishedDate = LocalDateTime.now(),
            totalStock = 10
        )

        val request = BookData(
            name = "책이름",
            author = "저자",
            isbn = "9788994327808",
            source = Source.PURCHASED,
            publishedDate = LocalDateTime.now(),
            totalStock = 10
        )

        When("도서 등록 요청시") {
            val actual = bookService.registerBook(request)
            Then("올바르게 등록된다") {
                actual.name shouldBe "책이름"
                actual.author shouldBe "저자"
                actual.source shouldBe Source.PURCHASED
                actual.bookStatus shouldBe BookStatus.AVAILABLE
                actual.currentStock shouldBe 10
                actual.totalStock shouldBe 10
            }
        }
    }

    afterTest {
        clearAllMocks()
    }
})
