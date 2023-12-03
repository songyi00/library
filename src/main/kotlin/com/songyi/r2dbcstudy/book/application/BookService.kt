package com.songyi.r2dbcstudy.book.application

import com.songyi.r2dbcstudy.book.domain.Book
import com.songyi.r2dbcstudy.book.adapter.persistence.entity.BookRepository
import com.songyi.r2dbcstudy.book.domain.vo.ISBN
import com.songyi.r2dbcstudy.book.port.out.BookCommandPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class BookService(
    private val bookCommandPort: BookCommandPort
) {
    @Transactional
    suspend fun register(request: BookData): BookResponse {
        val book = bookCommandPort.save(
            Book(
                name = request.name,
                author = request.author,
                isbn = ISBN(request.isbn),
                publishedTime = request.publishedTime
            )
        )
        return BookResponse.from(book)
    }
}