package app.models

import javax.persistence.{GeneratedValue,Id,GenerationType,Table,Entity}
import scala.beans.BeanProperty

/**
 * @author Krishna Reddy
 * Bean class to hold the movie information
 */

@Entity
@Table(name = "Movies")
class Movie {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @BeanProperty
  var id: Long = _

  @BeanProperty
  var title: String = _

  @BeanProperty
  var description: String = _
}