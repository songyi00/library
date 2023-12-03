package com.songyi.r2dbcstudy.book.application

import com.songyi.r2dbcstudy.book.domain.Book

data class BookData(
    val name: String,
    val author: String
)

data class BookResponse(
    val id: Long,
    val name: String,
    val author: String
) {
    companion object {
        fun from(book: Book): BookResponse {
            return BookResponse(book.id, book.name, book.author)
        }
    }
}