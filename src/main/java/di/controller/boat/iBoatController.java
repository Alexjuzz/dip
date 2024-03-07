package di.controller.boat;

import di.enums.TripType;
import di.model.dto.boats.ResponseBoat;
import jakarta.persistence.Enumerated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boat")
public interface iBoatController {

    @PostMapping("")
    public ResponseEntity<ResponseBoat> createBoatByNameAndCapacity();

    @GetMapping("/all")
    ResponseEntity<List<ResponseBoat>> getAll();

    @PostMapping("/setPlaces/{id}/{capacities}")
    ResponseEntity<ResponseBoat> setPlacesToBoat(@PathVariable("id") Long id,@PathVariable Integer capacities);

    @PostMapping("/setName/{id}/{name}")
    ResponseEntity<ResponseBoat> setNameToBoat(@PathVariable("id") Long id,@PathVariable String name);

    @PostMapping("/setTrip/{id}/{tripType}")
    ResponseEntity<ResponseBoat> setTripToBoat(@PathVariable("id") Long id, @PathVariable("tripType") TripType tripType);


}
