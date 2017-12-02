package app.repositories

import org.springframework.stereotype.Repository
import java.lang.Long
import org.springframework.data.repository.CrudRepository
import app.models.Movie

/**
 * @author Krishna Reddy
 * Repository to do CRUD operations of movie 
 */

@Repository
trait MovieRepository extends CrudRepository[Movie, Long] 