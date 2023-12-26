package com.study.library.book.adapter.out.persistence

import com.study.library.book.adapter.out.persistence.entity.BookRepository
import com.study.library.book.adapter.out.persistence.entity.getById
import com.study.library.book.domain.Book
import com.study.library.book.port.out.BookQueryPort
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
