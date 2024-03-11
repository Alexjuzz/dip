package di.controller.booking;

import di.enums.BookingTime;
import di.model.dto.booking.ResponseBooking;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//TODO : 1) Обработать возможные исключения и методы ответа.

@RestController
@RequestMapping("/booking")
public interface iBookingController {

    @PostMapping
    @RequestMapping("/setBookingToPlace")
    ResponseEntity<ResponseBooking> setBookingToPlace(@RequestParam("seatId") Long seatId,
                                                      @RequestParam("bookingTime") BookingTime bookingTime,
                                                      @RequestParam("number") String number);

    @PostMapping
    @RequestMapping("/changeBookingTime")
    ResponseEntity<ResponseBooking> changeReservedBookingTime(@RequestParam("seatId") Long seatId,
                                                              @RequestParam("oldTime") BookingTime oldTime,
                                                              @RequestParam("newTime") BookingTime newTime
    );

}
