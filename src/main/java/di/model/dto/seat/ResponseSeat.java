package di.model.dto.seat;

import di.model.entity.boats.AbstractBoat;
import di.model.entity.booking.Booking;
import lombok.Data;

@Data
public class ResponseSeat {
    private Long id;
    private AbstractBoat boat;
    private Booking booking;
}
