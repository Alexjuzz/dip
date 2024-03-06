package di.service.boat;


import di.enums.TripType;
import di.model.dto.boats.ResponseBoat;
import di.model.entity.boats.AbstractBoat;
import di.model.entity.boats.Boat;
import di.repository.boat.BoatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoatService {
    @Autowired
    private final BoatRepository repository;

    public BoatService(BoatRepository repository) {
        this.repository = repository;
    }

    public AbstractBoat  createBoatByNameAndCapacity(String boatName, int capacity){
        return new Boat(boatName,capacity);
    }


    /**
     * Метод конвертации обьекта из базы данных для ответа на фронт.
     * @param boat - Принимает обькт судна из базы данных и конвертирует для отправки на фронт
     * @return - возвращает обьект ответа.
     */

    private ResponseBoat convertBoatToResponseBoat(AbstractBoat boat){
        ResponseBoat response = new ResponseBoat();
        response.setId(boat.getId());
        response.setName(boat.getName());
        response.setTrip(boat.getTrip());
        response.setPlaces(boat.getPlaces());
        response.setTripType(boat.getTripType());
        return response;
    }

    /**
     * Метод конвертации принятого обьекта в обьект для сохранения.
     * @param boat - Принимает обькт принятый с фронта и конвертирует в обьект для сохранения в базе.
     * @return - возвращает обьект сохраняемый в базу
     */

    private AbstractBoat convertResponseBoatToBoat(ResponseBoat boat){
        AbstractBoat abstractBoat = new Boat();
        abstractBoat.setId(boat.getId());
        abstractBoat.setName(boat.getName());
        abstractBoat.setTrip(boat.getTrip());
        abstractBoat.setTripType(boat.getTripType());
        abstractBoat.setPlaces(boat.getPlaces());
        return abstractBoat;
    }

}
