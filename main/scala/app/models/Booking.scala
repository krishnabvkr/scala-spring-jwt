
package app.models

import scala.beans.BeanProperty

import javax.persistence.{Entity,GeneratedValue,Id,GenerationType,ManyToOne,CascadeType}


/**
 * @author Krishna Reddy
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
