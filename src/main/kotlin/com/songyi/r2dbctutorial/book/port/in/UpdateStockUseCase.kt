package com.songyi.r2dbctutorial.book.port.`in`

import com.songyi.r2dbctutorial.book.domain.Book

interface UpdateStockUseCase {
    suspend fun updateStock(stockData: StockData): Book
    data class StockData(
        val bookId: Long,
        val rentedCount: Int
    )
}