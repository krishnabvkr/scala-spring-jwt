package app.security

import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.{ EnableWebSecurity, WebSecurityConfigurerAdapter }
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

import SecurityUtils._


/**
 * @author Krishna Reddy
 * class to deal with web security of the application
 * 
 * */
@EnableWebSecurity
class WebSecurity(
    userDetailsService: UserDetailsService,
    bCryptPasswordEncoder: BCryptPasswordEncoder) extends WebSecurityConfigurerAdapter {

  @throws(classOf[Exception])
  override def configure(http: HttpSecurity): Unit = {
    http.csrf().disable().authorizeRequests()
      .antMatchers(HttpMethod.POST, SignUpURL).permitAll()
      .anyRequest().authenticated()
      .and()
      .addFilter(JWTAuthenticationFilter(authenticationManager(), LoginURL))
      .addFilter(new JWTAuthorizationFilter(authenticationManager()))
  }

  @throws(classOf[Exception])
  override def configure(auth: AuthenticationManagerBuilder): Unit = {
    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder)
  }

}
