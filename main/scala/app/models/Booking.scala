package app.models

import scala.beans.BeanProperty

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.GenerationType
import javax.persistence.ManyToOne
import javax.persistence.CascadeType


/**
 * @author 10358
 *Bean class to hold the booking information
 */
@Entity
class Booking extends Serializable {@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                        @BeanProperty  var id:Long=_
                        @BeanProperty var movieId:String=_
                        @BeanProperty var userEmail:String=_
                        @ManyToOne( cascade = Array(CascadeType.ALL))
                        @BeanProperty var cardInfo:CreditCardInfo=_                        
                          
}