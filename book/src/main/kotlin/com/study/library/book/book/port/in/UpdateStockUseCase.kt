package com.study.library.book.book.port.`in`

import com.study.library.book.book.domain.Book

interface UpdateStockUseCase {
    suspend fun updateStock(stockData: StockData): Book
    data class StockData(
        val bookId: Long,
        val rentedCount: Int
    )
}
