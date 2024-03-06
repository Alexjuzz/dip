package di.model.entity.seats;

import di.model.entity.boats.AbstractBoat;
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
    @Column(name = "seat_number") // позиция места
    private Integer set_number;

    @Column(name = "place_is_Occupied")  // занято или свободное
    private Boolean isOccupied;

    @ManyToOne
    @JoinColumn(name = "boat_id",nullable = false)
    private AbstractBoat boat;



}
