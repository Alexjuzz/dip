package di.service.booking;

import di.enums.BookingTime;
import di.model.dto.booking.ResponseBooking;
import di.model.entity.booking.Booking;
import di.model.entity.seats.Seat;
import di.repository.booking.BookingRepository;
import di.repository.seat.SeatRepository;
import di.service.boat.BoatService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BookingService {
        private final BookingRepository bookingRepository;
        private final BoatService boatService;



    @Autowired
    public BookingService(BookingRepository bookingRepository, BoatService boatService) {
        this.bookingRepository = bookingRepository;
        this.boatService = boatService;
    }





    private ResponseBooking convetBookingToResponseBooking(Booking booking) {
            ResponseBooking responseBooking = new ResponseBooking();
            responseBooking.setBookingTime(booking.getBookingTime());
            responseBooking.setDate(booking.getDate());
            responseBooking.setId(booking.getId());
            responseBooking.setSeat(booking.getSeat());
            return responseBooking;
    }
    private Booking convertResponseBookingToBooking(ResponseBooking responseBooking){
            Booking booking = new Booking();
            booking.setBookingTime(responseBooking.getBookingTime());
            booking.setId(responseBooking.getId());
            booking.setSeat(responseBooking.getSeat());
            booking.setDate(responseBooking.getDate());
            return booking;
    }
}
