package di.service.boat;

import di.enums.BookingTime;
import di.model.dto.booking.ResponseBooking;
import di.model.entity.booking.Booking;
import di.model.entity.seats.Seat;
import di.repository.boat.BookingRepository;
import di.repository.boat.SeatRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BookingService {
        private final SeatRepository seatRepository;
        private final BookingRepository bookingRepository;
        @Autowired

    public BookingService(SeatRepository seatRepository, BookingRepository bookingRepository) {
        this.seatRepository = seatRepository;
        this.bookingRepository = bookingRepository;
    }

    public ResponseBooking createBooking(Long seatId, LocalDate date, BookingTime bookingTime){
        Seat seat = seatRepository.findById(seatId).orElseThrow(()-> new EntityNotFoundException("Seat not found"));
        Booking booking = new Booking();
        booking.setSeat(seat);
        booking.setDate(date);
        booking.setBookingTime(bookingTime);
        boolean isAlreadyBooked = seat.getBookingTimes()
                .stream()
                .anyMatch(
                        b -> b.getDate().equals(date) && b.getBookingTime().equals(bookingTime));
        if (isAlreadyBooked) {
            throw new IllegalStateException("This seat is already booked for the selected time");
        }
        return convetBookingToResponseBooking(booking);
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
