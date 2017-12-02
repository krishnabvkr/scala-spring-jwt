package app.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.Date

/**
 * @author Krishna Reddy
 *  
 * */
object SecurityUtils {
  val SECRET = "SecretToSignJWT"
  val EXPIRATION_TIME = 3600000 // 1 hour
  val TOKEN_PREFIX = "Bearer "
  val HEADER_STRING = "Authorization"
  val SIGN_UP_URL = "/api/users/register"
  val LOGIN_URL = "/api/auth"
  
  
  /**To generate the JWT token for user authentication*/
  def generateToken(username:String):String= {
		 Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
				.compact();
	}
  
}
