package di.controller.booking;

import di.enums.BookingTime;
import di.model.dto.booking.ResponseBooking;
import di.service.booking.BookingService;
import org.apache.catalina.loader.ResourceEntry;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController implements iBookingController{
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public ResponseEntity<ResponseBooking> setBookingToPlace( Long seatId, BookingTime bookingTime) {
        return ResponseEntity.ok(bookingService.setBookingToPlace(seatId,bookingTime));
    }

    @Override
    public ResponseEntity<ResponseBooking> changeReservedBookingTime(Long seatId, BookingTime oldTime, BookingTime newTime) {
       return  ResponseEntity.ok(bookingService.changeReservedBookingTime(seatId,oldTime,newTime));
    }
}
