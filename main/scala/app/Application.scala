package app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

import com.fasterxml.jackson.databind.Module
import com.fasterxml.jackson.module.scala.DefaultScalaModule

@SpringBootApplication
class Application() {

  //Modulo Jackson per conversioni scala
  /*@Bean
  def parameterNamesModule(): Module = new DefaultScalaModule*/

  @Bean
  def bCryptPasswordEncoder() = new BCryptPasswordEncoder()

}

object Application extends App {
  SpringApplication.run(classOf[Application])
}
