package app.models

import scala.beans.BeanProperty

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GenerationType
import javax.persistence.GeneratedValue
import javax.persistence.Table

/**
 * @author 10358
 *Bean class to hold the user information
 */
@Entity
@Table(name="users")
 class ApplicationUser{
  @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
                            @BeanProperty var id: Long=_ 
                            @BeanProperty var emailId: String=_
                            @BeanProperty var password: String=_
}
                            
                            


