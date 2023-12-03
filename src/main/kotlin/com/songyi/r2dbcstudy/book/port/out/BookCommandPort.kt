package com.songyi.r2dbcstudy.book.port.out

import com.songyi.r2dbcstudy.book.domain.Book

interface BookCommandPort {
    fun save(book: Book): Book
    fun update(book: Book): Book
    fun delete(book: Book): Book
}