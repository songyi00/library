package com.songyi.r2dbcstudy.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("book")
class Book(
    @Id
    val id: Long = 0L,
    val name: String,
    val author: String
)