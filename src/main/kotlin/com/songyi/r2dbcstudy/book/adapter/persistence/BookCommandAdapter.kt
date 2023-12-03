package com.songyi.r2dbcstudy.book.adapter.persistence

import com.songyi.r2dbcstudy.book.domain.Book
import com.songyi.r2dbcstudy.book.port.out.BookCommandPort
import org.springframework.stereotype.Component

@Component
class BookCommandAdapter : BookCommandPort {
    override fun save(book: Book): Book {
        TODO("Not yet implemented")
    }

    override fun update(book: Book): Book {
        TODO("Not yet implemented")
    }

    override fun delete(book: Book): Book {
        TODO("Not yet implemented")
    }
}