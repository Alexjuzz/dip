package di.controller.booking;

import di.enums.BookingTime;
import di.model.dto.booking.ResponseBooking;
import org.apache.catalina.loader.ResourceEntry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public interface iBookingController {

    @PostMapping
    @RequestMapping("/setBookingToPlace")
    ResponseEntity<ResponseBooking> setBookingToPlace(@RequestParam("boatId") Long boatId,
                                                      @RequestParam("seatId") Long seatId,
                                                      @RequestParam("bookingTime") BookingTime bookingTime);

}
