package di.model.entity.seats;

import di.model.entity.boats.AbstractBoat;
import di.model.entity.boats.Boat;
import jakarta.persistence.*;
import lombok.Data;

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
    private Integer set_number;

    @Column(name = "placeIsOccupied")  // занято или свободное
    private Boolean isOccupied;

    @ManyToOne
    @JoinColumn(name = "boatId", nullable = false)
    private AbstractBoat boat;


}
