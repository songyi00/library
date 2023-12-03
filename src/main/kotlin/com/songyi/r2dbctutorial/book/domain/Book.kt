package com.songyi.r2dbctutorial.book.domain

import com.songyi.r2dbctutorial.book.domain.vo.ISBN
import java.time.LocalDateTime

class Book(
    val id: Long = 0L,
    val name: String,
    val author: String,
    val isbn: ISBN,
    val publishedTime: LocalDateTime
)