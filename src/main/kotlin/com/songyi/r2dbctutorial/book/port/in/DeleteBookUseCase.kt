package com.songyi.r2dbctutorial.book.port.`in`

import com.songyi.r2dbctutorial.book.domain.Book

interface DeleteBookUseCase {
    suspend fun deleteBook(bookId: Long)
}