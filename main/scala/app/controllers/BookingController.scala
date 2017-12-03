package app.controllers

import java.util.Calendar

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpHeaders,HttpStatus,ResponseEntity}
import org.springframework.web.bind.annotation.{PostMapping,RequestBody,RequestMapping,RestController}

import app.models.{Booking,CreditCardInfo}
import app.repositories.BookingRepository


/**
 * @author Krishna Reddy
 * Rest controller to handle the booking related requests
 * */

@RestController
@RequestMapping(Array("/api"))
class BookingController(@Autowired bookingRepository:BookingRepository ) {
  
  /**
   * To book a movie ticket by providing the 
   * user email, movie id, credit card details
   * */
  @PostMapping(Array("/book"))
  def bookTicket(@RequestBody booking: Booking) = {
    if(isValidateCardDetails(booking.cardInfo)){
    bookingRepository.save(booking)
    new ResponseEntity("success", new HttpHeaders, HttpStatus.OK)
    }else new ResponseEntity("invalid credit card information", new HttpHeaders, HttpStatus.BAD_REQUEST)
  }
  
  
  /**
   * To validate the given credit card details
   * */
  def isValidateCardDetails(cardDetails:CreditCardInfo):Boolean={
      val cal = Calendar.getInstance()
      if (cardDetails.name.size == 0) false
      else if (cardDetails.cardNumber.size != 16) false
      else if (cardDetails.cvvNumber.toString.size != 3) false
      else if (cardDetails.experiryMonth < 1 || cardDetails.experiryMonth > 12 || 
           (cardDetails.experiryYear == cal.get(Calendar.YEAR) && cardDetails.experiryMonth < cal.get(Calendar.MONTH))) false
      else if (cardDetails.experiryYear < cal.get(Calendar.YEAR)) false
      else true
  }
}