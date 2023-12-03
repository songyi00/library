package com.songyi.r2dbctutorial.book.adapter.persistence.entity

import com.songyi.r2dbctutorial.book.domain.Book
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : CoroutineCrudRepository<Book, Long>