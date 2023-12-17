package com.study.library.book.book.port.`in`

interface DeleteBookUseCase {
    suspend fun deleteBook(bookId: Long)
}
