package nexters.rent.adpater.out.persistnece

import nexters.rent.application.port.out.RentalCommandPort
import nexters.rent.domain.Rental
import org.springframework.stereotype.Component

@Component
class RentalCommandAdapter : RentalCommandPort {
    override fun saveBook(rental: Rental): Rental {
        TODO("Not yet implemented")
    }
}