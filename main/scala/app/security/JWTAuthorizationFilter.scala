package app.security

import scala.collection.JavaConverters._

import java.io.IOException
import javax.servlet.{FilterChain, ServletException}
import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

import org.springframework.security.authentication.{AuthenticationManager, UsernamePasswordAuthenticationToken}
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import SecurityUtils._
import io.jsonwebtoken.Jwts
import org.springframework.security.core.context.SecurityContextHolder

/**
 * @author Krishna Reddy
 * To get the Authorization for the user
 * */
class JWTAuthorizationFilter(authManager: AuthenticationManager) extends BasicAuthenticationFilter(authManager) {

  @throws(classOf[IOException])
  @throws(classOf[ServletException])
  override def doFilterInternal(
                                 request: HttpServletRequest,
                                 response: HttpServletResponse,
                                 chain: FilterChain,
                               ): Unit = {

    val header: String = request.getHeader(HEADER_STRING)

    if (header==null || !header.startsWith(TOKEN_PREFIX)) {
      chain.doFilter(request, response)
    } else {
      val authentication: UsernamePasswordAuthenticationToken = getAuthentication(request)
      SecurityContextHolder.getContext.setAuthentication(authentication)
      chain.doFilter(request, response)
    }

  }
  
/**
 * To get the authentication of the user
 * */
  private def getAuthentication(request: HttpServletRequest): UsernamePasswordAuthenticationToken = {

    val token: String = request.getHeader(HEADER_STRING)
    val user: String = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody().getSubject()

    if (user != null) {
      new UsernamePasswordAuthenticationToken(user, null, List().asJava)
    } else null

  }

}
