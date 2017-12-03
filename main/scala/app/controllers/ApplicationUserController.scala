package app.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation._

import app.models.ApplicationUser
import app.repositories.ApplicationUserRepository
import app.security.SecurityUtils
import javax.servlet.http.HttpServletResponse
import SecurityUtils._


/**
 * @author Krishna Reddy
 * 
 * Rest Controller to handle the user related requests
 * */
@RestController
@CrossOrigin
@RequestMapping(Array("/api/users"))
class ApplicationUserController(
    @Autowired applicationUserRepository: ApplicationUserRepository,
    @Autowired bCryptPasswordEncoder: BCryptPasswordEncoder) {

  
  /**
   * To register the new user     
   * */
  @PostMapping(Array("/register"))
  def signUp(@RequestBody user: ApplicationUser, res: HttpServletResponse) = {
    if (applicationUserRepository.findByEmailId(user.emailId) == null) {
      val hashedPassword = bCryptPasswordEncoder.encode(user.password)
      user.password_=(hashedPassword)
      applicationUserRepository.save(user)
      res.addHeader(HeaderString, TokenPrefix + " " + SecurityUtils.generateToken(user.emailId))
      new ResponseEntity("", new HttpHeaders, HttpStatus.OK)
    } else
      new ResponseEntity("user already taken", new HttpHeaders, HttpStatus.BAD_REQUEST)

  }

}
