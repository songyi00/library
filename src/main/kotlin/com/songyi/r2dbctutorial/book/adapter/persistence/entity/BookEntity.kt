package com.songyi.r2dbctutorial.book.adapter.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("book")
class BookEntity(
    @Id
    val id: Long = 0L,
    val name: String,
    val author: String,
    val isbn: Long,
    val publishedTime: LocalDateTime
)