package app.models

import scala.beans.BeanProperty
import javax.persistence.{Id,GeneratedValue,GenerationType,Entity}


/**
 * @author Krishna Reddy
 * Bean class to hold the Credit card information
 */

@Entity
class CreditCardInfo {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @BeanProperty var id: Long = _
  @BeanProperty var name: String = _
  @BeanProperty var cardNumber: String = _
  @BeanProperty var cvvNumber: Int = _
  @BeanProperty var experiryMonth: Int = _
  @BeanProperty var experiryYear: Int = _

}
  
