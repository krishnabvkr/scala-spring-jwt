package app.services

import scala.collection.JavaConverters._
import app.models.ApplicationUser
import app.repositories.ApplicationUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.{ User, UserDetails, UserDetailsService, UsernameNotFoundException }
import org.springframework.stereotype.Component

/**
 * @author Krishna Reddy
 * Component to validate the user while logging
 */

@Component
class UserDetailsServiceImpl(
    @Autowired applicationUserRepository: ApplicationUserRepository) extends UserDetailsService {

  @throws(classOf[UsernameNotFoundException])
  override def loadUserByUsername(emailId: String): UserDetails = {
    val applicationUser: ApplicationUser = applicationUserRepository.findByEmailId(emailId)
    if (applicationUser != null)
      new User(applicationUser.emailId, applicationUser.password, List().asJava)
    else
      throw new UsernameNotFoundException(emailId)
  }

}
