package com.songyi.r2dbctutorial.book.adapter.out.persistence

import com.songyi.r2dbctutorial.book.adapter.out.persistence.entity.BookEntity
import com.songyi.r2dbctutorial.book.adapter.out.persistence.entity.BookRepository
import com.songyi.r2dbctutorial.book.adapter.out.persistence.entity.getById
import com.songyi.r2dbctutorial.book.domain.Book
import com.songyi.r2dbctutorial.book.port.out.BookCommandPort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import org.springframework.stereotype.Component

@Component
class BookCommandAdapter(
    private val bookRepository: BookRepository
) : BookCommandPort {
    override suspend fun save(book: Book): Book {
        return bookRepository.save(BookEntity.from(book))
            .toDomain()
    }

    override suspend fun saveAll(books: List<Book>): Flow<Book> {
        val bookEntities = bookRepository.saveAll(
            books.map { BookEntity.from(it) }
                .asFlow()
        )
        return bookEntities.map { it.toDomain() }
    }

    override suspend fun update(book: Book): Book {
        return bookRepository.save(BookEntity.from(book))
            .toDomain()
    }

    override suspend fun delete(bookId: Long) {
        val bookEntity = bookRepository.getById(bookId)
        return bookRepository.delete(bookEntity)
    }
}
