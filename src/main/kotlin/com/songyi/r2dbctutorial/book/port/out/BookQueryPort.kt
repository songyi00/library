package com.songyi.r2dbctutorial.book.port.out

import com.songyi.r2dbctutorial.book.domain.Book

interface BookQueryPort {
    suspend fun loadBook(bookId: Long): Book
}