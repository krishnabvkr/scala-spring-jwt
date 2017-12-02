package app.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import scala.beans.BeanProperty
import javax.persistence.GenerationType
import javax.persistence.Table

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