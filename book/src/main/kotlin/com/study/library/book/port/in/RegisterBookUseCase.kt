package com.study.library.book.port.`in`

import com.study.library.book.domain.Book
import com.study.library.book.domain.Source
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
