package com.songyi.r2dbcstudy.domain

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : CoroutineCrudRepository<Book, Long> {

}