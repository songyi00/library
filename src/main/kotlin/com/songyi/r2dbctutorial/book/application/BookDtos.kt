package com.songyi.r2dbctutorial.book.application

import com.songyi.r2dbctutorial.book.domain.Book
import java.time.LocalDateTime

data class BookData(
    val name: String,
    val author: String,
    val isbn: Long,
    val publishedTime: LocalDateTime
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