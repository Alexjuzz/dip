package di.model.entity.seats;

import di.enums.BookingTime;
import di.model.entity.boats.AbstractBoat;
import di.model.entity.boats.Boat;
import di.model.entity.booking.Booking;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Класс места
 */
@Entity
@Table(name = "seat")
@Data
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "seatNumber") // позиция места
    private Integer setNumber;


    @ManyToOne                          // Ссылка на корабль
    @JoinColumn(name = "boatId", nullable = false)
    private AbstractBoat boat;

    @OneToMany(mappedBy = "seat", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Список всех всех времен
    private List<Booking> bookingTimes = new CopyOnWriteArrayList<>();
}
