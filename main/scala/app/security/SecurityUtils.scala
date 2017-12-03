package app.security

import io.jsonwebtoken.{Jwts,SignatureAlgorithm}
import java.util.Date

/**
 * @author Krishna Reddy
 *  
 * */
object SecurityUtils {
  val SECRET = "SecretToSignJWT"
  val ExpirationTime = 3600000 // 1 hour
  val TokenPrefix = "Bearer "
  val HeaderString = "Authorization"
  val SignUpURL = "/api/users/register"
  val LoginURL = "/api/auth"
  
  
  /**To generate the JWT token for user authentication*/
  def generateToken(username:String):String= {
		 Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + ExpirationTime))
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
				.compact();
	}
  
}
