package di.repository.boat;

import di.model.entity.seats.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository <Seat, Long> {
}
