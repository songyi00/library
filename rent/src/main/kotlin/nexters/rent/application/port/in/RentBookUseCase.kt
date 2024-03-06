package nexters.rent.application.port.`in`

import nexters.rent.domain.Rental

interface RentBookUseCase {
    fun rentBook(accountId: Long, bookId: Long): Rental
}