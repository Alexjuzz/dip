package di.DropTable;

import di.model.entity.boats.AbstractBoat;
import di.model.entity.boats.Boat;
import di.model.entity.booking.Booking;
import di.model.entity.seats.Seat;
import di.model.entity.trips.Trip;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service
public class DataBaseManagement {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void clearTables() {

        List<String> entityNames = List.of("AbstractBoat","Boat","Booking","Seat","Trip");

        entityNames.forEach(entityName -> {
            entityManager.createQuery("DELETE FROM " + entityName).executeUpdate();
        });
    }
}
