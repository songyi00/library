package com.study.library.book.book.port.out

import com.study.library.book.book.domain.Book

interface BookQueryPort {
    suspend fun loadBook(bookId: Long): Book
}
