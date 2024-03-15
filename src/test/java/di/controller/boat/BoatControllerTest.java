package di.controller.boat;

import di.enums.TripType;
import di.model.entity.boats.Boat;
import di.model.dto.boats.ResponseBoat;
import di.model.entity.seats.Seat;
import di.service.boat.BoatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class BoatControllerTest {

    @Mock
    private BoatService boatService;

    @InjectMocks
    private BoatController boatController;

    private Boat boat;
    private ResponseBoat responseBoat;

    @BeforeEach
    void setUp() {
        boat = new Boat();
        responseBoat = new ResponseBoat();
    }

    @Test
    void createBoat() {
        when(boatService.createBoatByNameAndCapacity(any(Boat.class))).thenReturn(responseBoat);

        ResponseEntity<ResponseBoat> result = boatController.createBoat(boat);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());

        verify(boatService, times(1)).createBoatByNameAndCapacity(any(Boat.class));
    }
    @Test
    void getAll_shouldReturnBoats_whenBoatsExist() {
        List<ResponseBoat> boats = List.of(new ResponseBoat(), new ResponseBoat());

        when(boatService.getAllBoats()).thenReturn(boats);

        ResponseEntity<List<ResponseBoat>> response = boatController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());

        verify(boatService, times(1)).getAllBoats();
    }
    @Test
    void getSeatsById_shouldReturnSeats_whenSeatsExist() {
        List<Seat> seats = List.of(new Seat(), new Seat()); // Инициализируйте тестовые данные для мест

        when(boatService.getSeatsByBoatId(anyLong())).thenReturn(seats);

        ResponseEntity<List<Seat>> response = boatController.getSeatsById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());

        verify(boatService, times(1)).getSeatsByBoatId(anyLong());
    }
    @Test
    void getBoatById_shouldReturnBoat_whenBoatExists() {
        ResponseBoat boat = new ResponseBoat();

        when(boatService.getBoatById(anyLong())).thenReturn(boat);

        ResponseEntity<ResponseBoat> response = boatController.getBoatById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        verify(boatService, times(1)).getBoatById(anyLong());
    }
    @Test
    void setPlacesToBoat_shouldUpdateBoatPlaces() {
        ResponseBoat updatedBoat = new ResponseBoat();

        when(boatService.setPlacesToBoat(eq(1L), anyInt())).thenReturn(updatedBoat);

        ResponseEntity<ResponseBoat> response = boatController.setPlacesToBoat(1L, 100);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        verify(boatService, times(1)).setPlacesToBoat(eq(1L), anyInt());
    }
    @Test
    void setNameToBoat_shouldUpdateBoatName() {
        ResponseBoat updatedBoat = new ResponseBoat();

        when(boatService.setNameToBoat(eq(1L), anyString())).thenReturn(updatedBoat);

        ResponseEntity<ResponseBoat> response = boatController.setNameToBoat(1L, "New Name");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        verify(boatService, times(1)).setNameToBoat(eq(1L), anyString());
    }

    @Test
    void setTripToBoat_shouldUpdateBoatTrip() {
        ResponseBoat updatedBoat = new ResponseBoat();

        when(boatService.setTripBoat(eq(1L), any(TripType.class))).thenReturn(updatedBoat);

        ResponseEntity<ResponseBoat> response = boatController.setTripToBoat(1L, TripType.REGULAR);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        verify(boatService, times(1)).setTripBoat(eq(1L), any(TripType.class));
    }

}
