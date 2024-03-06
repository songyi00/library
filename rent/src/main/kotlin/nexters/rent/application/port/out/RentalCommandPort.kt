package nexters.rent.application.port.out

import nexters.rent.domain.Rental

interface RentalCommandPort {
    fun saveBook(rental: Rental): Rental
}