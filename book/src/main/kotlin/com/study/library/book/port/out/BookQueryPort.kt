package com.study.library.book.port.out

import com.study.library.book.domain.Book

interface BookQueryPort {
    suspend fun loadBook(bookId: Long): Book
}
