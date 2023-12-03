package com.songyi.r2dbcstudy.book.domain

import com.songyi.r2dbcstudy.book.domain.vo.ISBN
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Embedded
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

class Book(
    val id: Long = 0L,
    val name: String,
    val author: String,
    val isbn: ISBN,
    val publishedTime: LocalDateTime
)