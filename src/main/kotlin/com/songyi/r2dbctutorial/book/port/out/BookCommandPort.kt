package com.songyi.r2dbctutorial.book.port.out

import com.songyi.r2dbctutorial.book.domain.Book

interface BookCommandPort {
    fun save(book: Book): Book
    fun update(book: Book): Book
    fun delete(book: Book): Book
}