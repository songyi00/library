package nexters.rent.application

import nexters.rent.application.port.`in`.RentBookUseCase
import nexters.rent.domain.Rental
import nexters.rent.domain.RentalStatus
import org.springframework.stereotype.Service

@Service
class RentBookService(

) : RentBookUseCase {

    override fun rentBook(accountId: Long, bookId: Long): Rental {
        return Rental(accountId, bookId, RentalStatus.LOAN)
    }
}