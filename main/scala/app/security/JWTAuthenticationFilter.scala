package app.security

import java.io.IOException
import java.util.Date
import javax.servlet.{ FilterChain, ServletException }

import scala.collection.JavaConverters._
import javax.servlet.http.{ HttpServletRequest, HttpServletResponse }

import app.models.ApplicationUser
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.{ Jwts, SignatureAlgorithm }
import org.springframework.security.authentication.{ AuthenticationManager, UsernamePasswordAuthenticationToken }
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import SecurityUtils._
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.springframework.security.web.util.matcher.AntPathRequestMatcher



object JWTAuthenticationFilter {
  def apply(
    authenticationManager: AuthenticationManager,
    url: String): JWTAuthenticationFilter = {
    val result = new JWTAuthenticationFilter(authenticationManager)
    result.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(url, "POST"))
    result
  }
}

class JWTAuthenticationFilter(
    authenticationManager: AuthenticationManager) extends UsernamePasswordAuthenticationFilter {

  @throws(classOf[RuntimeException])
  override def attemptAuthentication(request: HttpServletRequest,
                                     response: HttpServletResponse): Authentication = {

    try {
      val creds: ApplicationUser = new ObjectMapper().registerModule(DefaultScalaModule)
        .readValue(request.getInputStream, classOf[ApplicationUser])
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        creds.emailId,
        creds.password,
        List().asJava))
    } catch {
      case e: IOException => throw new RuntimeException(e.toString)
    }

  }

  @throws(classOf[IOException])
  @throws(classOf[ServletException])
  override def successfulAuthentication(
    request: HttpServletRequest,
    response: HttpServletResponse,
    chain: FilterChain,
    auth: Authentication): Unit = {

    val token: String = Jwts.builder()
      .setSubject(auth.getPrincipal.asInstanceOf[User].getUsername)
      .setExpiration(new Date(System.currentTimeMillis + EXPIRATION_TIME))
      .signWith(SignatureAlgorithm.HS512, SECRET)
      .compact()
    response.addHeader(HEADER_STRING, TOKEN_PREFIX + token)

  }

}