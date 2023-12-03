package com.songyi.r2dbcstudy.book.application

import com.songyi.r2dbcstudy.book.application.BookData
import com.songyi.r2dbcstudy.book.application.BookResponse
import com.songyi.r2dbcstudy.book.domain.Book
import com.songyi.r2dbcstudy.book.domain.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalArgumentException

@Service
class BookService(
    private val bookRepository: BookRepository
) {
    @Transactional
    suspend fun register(request: BookData): BookResponse {
        val book = bookRepository.save(
            Book(name = request.name, author = request.author)
        )
        return BookResponse.from(book)
    }
}