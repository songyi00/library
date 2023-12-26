package com.study.library.book.adapter.out.persistence.entity

import com.study.library.book.domain.Book
import com.study.library.book.domain.BookStatus
import com.study.library.book.domain.Source
import com.study.library.book.domain.vo.ISBN
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("book")
class BookEntity(
    @Id
    val id: Long = 0L,
    val name: String,
    val author: String,
    val isbn: String,
    val source: Source,
    val bookStatus: BookStatus,
    val publishedDate: LocalDateTime,
    val currentStock: Int,
    val totalStock: Int
) {
    companion object {
        fun from(book: Book): BookEntity {
            return BookEntity(
                id = book.id,
                name = book.name,
                author = book.author,
                isbn = book.isbn.value,
                source = book.source,
                bookStatus = book.bookStatus,
                currentStock = book.currentStock,
                totalStock = book.totalStock,
                publishedDate = book.publishedDate
            )
        }
    }

    fun toDomain(): Book {
        return Book(
            id = this.id,
            name = this.name,
            author = this.author,
            isbn = ISBN(this.isbn),
            source = this.source,
            bookStatus = this.bookStatus,
            currentStock = this.currentStock,
            totalStock = this.totalStock,
            publishedDate = this.publishedDate
        )
    }
}
