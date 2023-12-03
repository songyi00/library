package com.songyi.r2dbctutorial

import com.songyi.r2dbctutorial.book.application.BookData
import com.songyi.r2dbctutorial.book.application.BookService
import com.songyi.r2dbctutorial.book.adapter.persistence.entity.BookRepository
import com.songyi.r2dbctutorial.config.IntegrationTest
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

@IntegrationTest
class BookServiceTest(
    private val bookService: BookService
) : BehaviorSpec({

    Given("정상적인 도서 데이터인 경우") {
        val request = BookData("책이름", "저자", 9788994327808, LocalDateTime.now())
        When("도서 등록 요청시") {
            val actual = bookService.register(request)
            Then("올바르게 등록된다") {
                actual.name shouldBe "책이름"
                actual.author shouldBe "저자"
            }
        }
    }
})
