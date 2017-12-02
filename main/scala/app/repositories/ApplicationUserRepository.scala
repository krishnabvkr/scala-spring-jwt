package app.repositories

import org.springframework.stereotype.Repository
import org.springframework.data.repository.CrudRepository
import app.models.ApplicationUser
import java.lang.Long

/**
 * @author Krishna Reddy
 * Repository to do CRUD operations of user
 */

@Repository
trait ApplicationUserRepository extends CrudRepository [ApplicationUser, Long]{
           def findByEmailId( emailId:String):ApplicationUser
}
