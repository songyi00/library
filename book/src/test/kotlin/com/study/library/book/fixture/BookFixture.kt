package com.study.library.book.fixture

import com.study.library.book.adapter.out.persistence.entity.BookEntity
import com.study.library.book.domain.Book
import com.study.library.book.domain.BookStatus
import com.study.library.book.domain.Source
import com.study.library.book.domain.vo.ISBN
import com.study.library.book.port.`in`.RegisterBookUseCase
import java.time.LocalDateTime

const val NAME = "clean code"
const val AUTHOR = "로버트 마틴"
const val ISBN = "1234567891234"
val PUBLISHED_DATE: LocalDateTime = LocalDateTime.of(2023, 3, 12, 0, 0)

fun createBookData(
    name: String = NAME,
    author: String = AUTHOR,
    isbn: String = ISBN,
    source: Source = Source.PURCHASED,
    totalStock: Int = 10,
    publishedDate: LocalDateTime = PUBLISHED_DATE,
): RegisterBookUseCase.BookData {
    return RegisterBookUseCase.BookData(
        name = name,
        author = author,
        isbn = isbn,
        source = source,
        totalStock = totalStock,
        publishedDate = publishedDate
    )
}

fun createBook(
    name: String = NAME,
    author: String = AUTHOR,
    isbn: String = ISBN,
    source: Source = Source.PURCHASED,
    totalStock: Int = 10,
    publishedDate: LocalDateTime = PUBLISHED_DATE,
): Book {
    return Book(name, author, ISBN(isbn), source, totalStock, publishedDate)
}

fun createBookEntity(
    name: String = NAME,
    author: String = AUTHOR,
    isbn: String = ISBN,
    source: Source = Source.PURCHASED,
    bookStatus: BookStatus = BookStatus.AVAILABLE,
    totalStock: Int = 10,
    publishedDate: LocalDateTime = PUBLISHED_DATE,
    id: Long = 0L
): BookEntity {
    return BookEntity(
        id = id, name = name,
        author = author,
        isbn = isbn,
        source = source,
        bookStatus = bookStatus,
        totalStock = totalStock,
        currentStock = totalStock,
        publishedDate = publishedDate
    )
}
