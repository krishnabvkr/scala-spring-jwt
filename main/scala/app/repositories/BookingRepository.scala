package app.repositories


import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

import app.models.Booking
import java.lang.Long


/**
 * @author Krishna Reddy
 * Repository to do CRUD operations of Booking
 */

@Repository
trait BookingRepository extends CrudRepository[Booking, Long] 