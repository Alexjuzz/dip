package di.controller.boat;


import di.enums.TripType;
import di.model.dto.boats.ResponseBoat;
import di.model.entity.boats.Boat;
import di.service.boat.BoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BoatController implements iBoatController {
    private final BoatService boatService;

    @Autowired

    public BoatController(BoatService boatService) {
        this.boatService = boatService;
    }


    @Override
    public ResponseEntity<ResponseBoat> createBoatByNameAndCapacity(Boat boat) {
        return ResponseEntity.ok(boatService.createBoatByNameAndCapacity(boat));
    }

    @Override
    public ResponseEntity<List<ResponseBoat>> getAll() {
        return ResponseEntity.ok(boatService.getAllBoats());
    }

    @Override
    public ResponseEntity<ResponseBoat> setPlacesToBoat(Long id, Integer capacities) {
        return ResponseEntity.ok(boatService.setPlacesToBoat(id,capacities));
    }

    @Override
    public ResponseEntity<ResponseBoat> setNameToBoat(Long id,String  name) {
        return ResponseEntity.ok(boatService.setNameToBoat(id,name));
    }

    @Override
    public ResponseEntity<ResponseBoat> setTripToBoat(Long id, TripType tripType) {
        return  ResponseEntity.ok(boatService.setTripBoat(id,tripType));
    }


}
