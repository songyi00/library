package com.songyi.r2dbctutorial.book.adapter.out.persistence

import com.songyi.r2dbctutorial.book.adapter.out.persistence.entity.BookRepository
import com.songyi.r2dbctutorial.book.adapter.out.persistence.entity.getById
import com.songyi.r2dbctutorial.book.domain.Book
import com.songyi.r2dbctutorial.book.port.out.BookQueryPort
import org.springframework.stereotype.Component

@Component
class BookQueryAdapter(
    private val bookRepository: BookRepository
) : BookQueryPort {

    override suspend fun loadBook(bookId: Long): Book {
        return bookRepository.getById(bookId)
            .toDomain()
    }
}
