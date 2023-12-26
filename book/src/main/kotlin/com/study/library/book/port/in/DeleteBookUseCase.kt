package com.study.library.book.port.`in`

interface DeleteBookUseCase {
    suspend fun deleteBook(bookId: Long)
}
