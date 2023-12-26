package com.study.library.book.application

import com.study.library.book.domain.Book
import com.study.library.book.domain.vo.ISBN
import com.study.library.book.port.`in`.DeleteBookUseCase
import com.study.library.book.port.`in`.RegisterBookUseCase
import com.study.library.book.port.`in`.RegisterBookUseCase.BookData
import com.study.library.book.port.`in`.UpdateStockUseCase
import com.study.library.book.port.out.BookCommandPort
import com.study.library.book.port.out.BookQueryPort
import mu.KotlinLogging
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookService(
    private val bookCommandPort: BookCommandPort,
    private val bookQueryPort: BookQueryPort
) : RegisterBookUseCase, UpdateStockUseCase, DeleteBookUseCase {

    private val logger = KotlinLogging.logger {}

    @Transactional
    override suspend fun registerBook(request: BookData): Book {
        return bookCommandPort.save(
            Book(
                name = request.name,
                author = request.author,
                isbn = ISBN(request.isbn),
                source = request.source,
                publishedDate = request.publishedDate,
                totalStock = request.totalStock
            )
        )
    }

    @Transactional
    override suspend fun updateStock(stockData: UpdateStockUseCase.StockData): Book {
        return try {
            val book = bookQueryPort.loadBook(stockData.bookId)
            book.updateStock(stockData.rentedCount)
            bookCommandPort.update(book)
        } catch (e: OptimisticLockingFailureException) {
            logger.warn { "optimistic locking failure in updateStock [stockData: $stockData]" }
            updateStock(stockData)
        }
    }

    @Transactional
    override suspend fun deleteBook(bookId: Long) {
        bookCommandPort.delete(bookId)
    }
}
