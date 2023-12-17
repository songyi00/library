package com.songyi.r2dbctutorial.book.port.`in`

import com.songyi.r2dbctutorial.book.domain.Book
import com.songyi.r2dbctutorial.book.domain.Source
import java.time.LocalDateTime

interface RegisterBookUseCase {
    suspend fun registerBook(request: BookData): Book

    data class BookData(
        val name: String,
        val author: String,
        val isbn: String,
        val source: Source,
        val publishedDate: LocalDateTime,
        val totalStock: Int
    )
}
