package di.service.booking;

import di.enums.BookingTime;
import di.model.dto.booking.ResponseBooking;
import di.model.entity.boats.AbstractBoat;
import di.model.entity.booking.Booking;
import di.model.entity.seats.Seat;
import di.repository.boat.BoatRepository;
import di.repository.booking.BookingRepository;
import di.repository.seat.SeatRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final BoatRepository boatRepository;
    private final SeatRepository seatRepository;


    @Autowired
    public BookingService(BookingRepository bookingRepository, BoatRepository boatRepository, SeatRepository seatRepository) {
        this.bookingRepository = bookingRepository;
        this.boatRepository = boatRepository;
        this.seatRepository = seatRepository;
    }

    public ResponseBooking setBookingToPlace(Long boatId, Long seatId, BookingTime bookingTime) {
        AbstractBoat boat = boatRepository.findById(boatId)
                .orElseThrow(() -> new NoSuchElementException("Boat not found"));

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new NoSuchElementException("Seat not found"));

        Booking booking = new Booking();
        booking.setDate(LocalDate.now());
        booking.setSeat(seat);
        booking.setBookingTime(bookingTime);


        return convetBookingToResponseBooking(bookingRepository.save(booking));
    }


    private ResponseBooking convetBookingToResponseBooking(Booking booking) {
        ResponseBooking responseBooking = new ResponseBooking();
        responseBooking.setBookingTime(booking.getBookingTime());
        responseBooking.setDate(booking.getDate());
        responseBooking.setId(booking.getId());
        responseBooking.setSeat(booking.getSeat());
        return responseBooking;
    }

    private Booking convertResponseBookingToBooking(ResponseBooking responseBooking) {
        Booking booking = new Booking();
        booking.setBookingTime(responseBooking.getBookingTime());
        booking.setId(responseBooking.getId());
        booking.setSeat(responseBooking.getSeat());
        booking.setDate(responseBooking.getDate());
        return booking;
    }
}
