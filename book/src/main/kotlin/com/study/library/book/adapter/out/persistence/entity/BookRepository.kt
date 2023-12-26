package com.study.library.book.adapter.out.persistence.entity

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

suspend fun BookRepository.getById(bookId: Long): BookEntity {
    return findById(bookId) ?: throw IllegalArgumentException("존재하지 않는 book입니다. [id: $bookId]")
}

@Repository
interface BookRepository : CoroutineCrudRepository<BookEntity, Long>
