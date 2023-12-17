package com.songyi.r2dbctutorial.book.port.out

import com.songyi.r2dbctutorial.book.domain.Book
import kotlinx.coroutines.flow.Flow

interface BookCommandPort {
    suspend fun save(book: Book): Book
    suspend fun saveAll(books: List<Book>): Flow<Book>
    suspend fun update(book: Book): Book
    suspend fun delete(bookId: Long)
}
