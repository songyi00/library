package com.songyi.r2dbctutorial.book.domain

import com.songyi.r2dbctutorial.book.domain.vo.ISBN
import java.time.LocalDateTime

/**
 * 재고 도서
 * @property name 도서명
 * @property author 저자
 * @property isbn ISBN
 * @property source 출처
 * @property bookStatus 대출 가능 여부
 * @property publishedDate 출판일
 */
class Book(
    val id: Long = 0L,
    val name: String,
    val author: String,
    val isbn: ISBN,
    val source: Source,
    var bookStatus: BookStatus,
    var currentStock: Int,
    val totalStock: Int,
    val publishedDate: LocalDateTime
) {
    constructor(
        name: String,
        author: String,
        isbn: ISBN,
        source: Source,
        totalStock: Int,
        publishedDate: LocalDateTime
    ) : this(
        id = 0L,
        name,
        author,
        isbn,
        source,
        BookStatus.AVAILABLE,
        currentStock = totalStock,
        totalStock,
        publishedDate
    )

    fun updateStock(rentedCount: Int) {
        currentStock = totalStock - rentedCount
        if (currentStock <= 0) {
            unableRental()
        }
    }

    private fun unableRental() {
        bookStatus = BookStatus.UNAVAILABLE
    }
}
