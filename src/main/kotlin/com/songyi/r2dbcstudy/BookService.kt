package com.songyi.r2dbcstudy

import com.songyi.r2dbcstudy.application.BookData
import com.songyi.r2dbcstudy.application.BookResponse
import com.songyi.r2dbcstudy.domain.Book
import com.songyi.r2dbcstudy.domain.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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