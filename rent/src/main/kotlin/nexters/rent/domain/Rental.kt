package nexters.rent.domain

class Rental(
    val accountId: Long,
    val bookId: Long,
    val rentalStatus: RentalStatus
)