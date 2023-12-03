package com.songyi.r2dbcstudy

import com.songyi.r2dbcstudy.application.BookData
import com.songyi.r2dbcstudy.config.IntegrationTest
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldNotBe

@IntegrationTest
class BookServiceTest(
    private val bookService: BookService
) : BehaviorSpec({

    Given("정상적인 도서 데이터인 경우") {
        val request = BookData("책이름", "저자")
        When("도서 등록 요청시") {
            val actual = bookService.register(request)
            Then("올바르게 등록된다") {
                actual.name shouldNotBe null
            }
        }

        When("예외 발생 시") {
            Then("롤백된다.") {

            }
        }
    }

})
