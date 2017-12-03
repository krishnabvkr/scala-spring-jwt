package app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class Application() {

  @Bean
  def bCryptPasswordEncoder() = new BCryptPasswordEncoder()
}

object Application extends App {
  SpringApplication.run(classOf[Application])
}
