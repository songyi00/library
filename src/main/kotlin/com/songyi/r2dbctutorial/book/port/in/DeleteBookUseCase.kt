package com.songyi.r2dbctutorial.book.port.`in`

interface DeleteBookUseCase {
    suspend fun deleteBook(bookId: Long)
}
