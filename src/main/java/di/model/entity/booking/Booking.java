package di.model.entity.booking;

import di.enums.BookingTime;
import di.model.entity.seats.Seat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "seat_id", referencedColumnName = "id")
    private Seat seat;
    @Enumerated(EnumType.STRING)
    private BookingTime bookingTime;
    private LocalDate date;
}
