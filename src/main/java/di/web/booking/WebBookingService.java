package di.web.booking;

import di.customexceptions.booking.BookingNotFoundException;
import di.customexceptions.seat.SeatAlreadyBookedException;
import di.customexceptions.seat.SeatNotFoundException;
import di.customexceptions.telephone.InvalidPhoneNumberException;
import di.customexceptions.telephone.TelephoneNotFoundException;
import di.customexceptions.user.UserNotFoundException;
import di.enums.BookingTime;
import di.model.dto.booking.ResponseBooking;
import di.model.entity.booking.Booking;
import di.model.entity.seats.Seat;
import di.model.entity.telephone.Telephone;
import di.model.entity.user.User;
import di.repository.boat.BoatRepository;
import di.repository.booking.BookingRepository;
import di.repository.seat.SeatRepository;
import di.repository.telephone.TelephoneRepository;
import di.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class WebBookingService {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final TelephoneRepository telephoneRepository;
    private final SeatRepository seatRepository;

    @Autowired
    public WebBookingService(UserRepository userRepository, BookingRepository bookingRepository,TelephoneRepository telephoneRepository,SeatRepository seatRepository) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.telephoneRepository = telephoneRepository;
        this.seatRepository = seatRepository;
    }

    public ResponseBooking makeBooking(Long seatId, String time,User user){

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new SeatNotFoundException("Seat with ID: " + seatId + " not found"));

        BookingTime bookingTime = BookingTime.fromString(time);
        Telephone telephone = user.getTelephone();
        if (!checkReservedPlace(seat, bookingTime)) {
            throw new SeatAlreadyBookedException("The seat is already booked for the selected time");
        }

        Booking booking = new Booking();
        booking.setDate(LocalDate.now());
        booking.setSeat(seat);
        booking.setBookingTime(bookingTime);
        booking.setTelephone(telephone);
        return convetBookingToResponseBooking(bookingRepository.save(booking));
    }
    @Transactional
    public boolean cancelReservation(Long seatId, BookingTime bookingTime, String phoneNumber) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new SeatNotFoundException("Seat not found"));


        //поиск забранированного времени в списке всех броней.
        Booking bookingToRemove = seat.getBookings().stream()
                .filter(booking -> booking.getBookingTime().equals(bookingTime))
                .findFirst()
                .orElseThrow(() -> new BookingNotFoundException("Booking not found"));

        Telephone tel = telephoneRepository.getTelephoneByNumber(phoneNumber).orElseThrow(() -> new TelephoneNotFoundException("Phone with number:" + phoneNumber + " is not found"));
        if (bookingToRemove.getTelephone().getId().equals(tel.getId())) {
            bookingRepository.delete(bookingToRemove); // Удаляем бронирование
        } else {
            throw new InvalidPhoneNumberException("Invalid phone number, reservation was recorded on another number");
        }


        return true;
    }
    private boolean checkReservedPlace(Seat seat, BookingTime bookingTime) {
        return seat.getBookings().stream()
                .noneMatch(b -> b.getBookingTime().equals(bookingTime));
    }

    private ResponseBooking convetBookingToResponseBooking(Booking booking) {
        ResponseBooking responseBooking = new ResponseBooking();
        responseBooking.setBookingTime(booking.getBookingTime());
        responseBooking.setDate(booking.getDate());
        responseBooking.setId(booking.getId());
        responseBooking.setSeat(booking.getSeat());
        return responseBooking;
    }
}
