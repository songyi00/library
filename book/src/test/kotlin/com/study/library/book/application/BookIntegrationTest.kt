package com.study.library.book.application

import com.songyi.r2dbctutorial.config.IntegrationTest
import com.study.library.book.book.adapter.out.persistence.entity.BookRepository
import com.study.library.book.book.application.BookService
import com.study.library.book.book.domain.BookStatus
import com.study.library.book.book.port.`in`.UpdateStockUseCase
import com.study.library.book.fixture.createBookData
import com.study.library.book.fixture.createBookEntity
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

@IntegrationTest
class BookIntegrationTest(
    private val bookRepository: BookRepository,
    private val bookService: BookService
) : BehaviorSpec({

    afterTest {
        bookRepository.deleteAll()
    }

    Given("정상적인 도서 데이터인 경우") {
        val request = createBookData()

        When("도서 등록 요청시") {
            val actual = bookService.registerBook(request)

            Then("올바르게 등록된다") {
                actual.name shouldBe request.name
                actual.author shouldBe request.author
                actual.source shouldBe request.source
                actual.bookStatus shouldBe BookStatus.AVAILABLE
                actual.currentStock shouldBe request.totalStock
                actual.totalStock shouldBe request.totalStock
            }
        }
    }

    Given("정상적이지 않은 도서 데이터인 경우") {
        When("도서 등록 요청시") {
            Then("예외가 발생한다") {
                shouldThrow<IllegalArgumentException> {
                    bookService.registerBook(createBookData(isbn = "123"))
                }
            }
        }
    }

    Given("재고 변경이 필요한 경우") {
        val book = bookRepository.save(createBookEntity(totalStock = 5))
        val request = UpdateStockUseCase.StockData(
            bookId = book.id,
            rentedCount = 2
        )

        When("재고를 변경하면") {
            val actual = bookService.updateStock(request)
            Then("정상적으로 재고가 변경된다.") {
                actual.name shouldBe book.name
                actual.author shouldBe book.author
                actual.source shouldBe book.source
                actual.bookStatus shouldBe book.bookStatus
                actual.currentStock shouldBe 3
                actual.totalStock shouldBe 5
            }
        }
    }
})
