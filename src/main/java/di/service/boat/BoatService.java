package di.service.boat;


import di.enums.BookingTime;
import di.model.dto.boats.ResponseBoat;
import di.model.entity.boats.AbstractBoat;
import di.model.entity.boats.Boat;
import di.model.entity.booking.Booking;
import di.model.entity.seats.Seat;
import di.repository.boat.BoatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoatService {
    @Autowired
    private final BoatRepository repository;
    private BookingTime booking;
    public BoatService(BoatRepository repository) {
        this.repository = repository;
    }

    public AbstractBoat createBoatByNameAndCapacity(String boatName, int capacity) {
        return new Boat(boatName, capacity);
    }


    /**
     * Метод поиска судна по его ID
     *
     * @param id - ID судна в базе данных
     * @return - возращает обьект ответа ResponseBoat
     * <p>
     * TODO - доделать логирование
     */
    public Optional<ResponseBoat> getBoatById(Long id) {

        Optional<ResponseBoat> responseBoat = repository.findById(id).map(this::convertBoatToResponseBoat);

        if (responseBoat.isPresent()) {
            System.out.printf("Boat with id {%s} found", id);
        } else {
            System.out.printf("Boat with id {%s} not found", id);
        }
        return responseBoat;
    }

    /**
     * Метод бронирования места на судне.
     *
     * @param boatId  - Id искомого судна
     * @param placeId - Id места на судне(индекс в ArrayList-e)
     * @return Возвращается обьект ответа ResponseBoat
     */

    public ResponseBoat takePlace(Long boatId, Integer placeId) {
        BookingTime btime;
        AbstractBoat boat = repository.findById(boatId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Boat with ID %s not found", boatId))); // поиск судна в списке

        if (placeId < 0 || placeId >= boat.getPlaces().size()) {   // проверка ID места находящегося в текущем судне
            throw new IllegalArgumentException(String.format("Incorrect place ID: %s", placeId)); //
        }

        Seat seat = boat.getPlaces().get(placeId); // Создание обьекта искомого места
        if(seat.getBookingTimes().contains(bookingTime)){
            throw new IllegalArgumentException("Place already took at the time: " +  bookingTime.getTime());
        }else {
                btime =
        }
        seat.get
        seat.setIsOccupied(true);  // Установка фалага занятого места
        boat = repository.save(boat);

        System.out.printf("Place %s has been taken%n", placeId);
        return convertBoatToResponseBoat(boat);
    }
    /**
     * Метод снятия бронирования места на судне.
     *
     * @param boatId  - Id искомого судна
     * @param placeId - Id места на судне(индекс в ArrayList-e)
     * @return Возвращается обьект ответа ResponseBoat
     */
    public ResponseBoat unlockPlace(Long boatId, Integer placeId) {
        AbstractBoat boat = repository.findById(boatId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Boat with ID %s not found", boatId)));

        if (placeId < 0 || placeId >= boat.getPlaces().size()) {   // проверка ID места находящегося в текущем судне
            throw new IllegalArgumentException(String.format("Incorrect place ID: %s", placeId)); //
        }

        Seat seat = boat.getPlaces().get(placeId);

        if (!seat.getIsOccupied()) {                                // если место было свободно то выбросится исключение
            throw new IllegalStateException(String.format("Place %s is not taken", placeId));
        }

        seat.setIsOccupied(true);  // Установка фалага свободного места места
        boat = repository.save(boat);
        System.out.printf("Place %s has been unlocked%n", placeId);
        return convertBoatToResponseBoat(boat);
    }




    // region convert methods

    /**
     * Метод конвертации обьекта из базы данных для ответа на фронт.
     *
     * @param boat - Принимает обькт судна из базы данных и конвертирует для отправки на фронт
     * @return - возвращает обьект ответа.
     */

    private ResponseBoat convertBoatToResponseBoat(AbstractBoat boat) {
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
     *
     * @param boat - Принимает обькт принятый с фронта и конвертирует в обьект для сохранения в базе.
     * @return - возвращает обьект сохраняемый в базу
     */

    private AbstractBoat convertResponseBoatToBoat(ResponseBoat boat) {
        AbstractBoat abstractBoat = new Boat();
        abstractBoat.setId(boat.getId());
        abstractBoat.setName(boat.getName());
        abstractBoat.setTrip(boat.getTrip());
        abstractBoat.setTripType(boat.getTripType());
        abstractBoat.setPlaces(boat.getPlaces());
        return abstractBoat;
    }
    //endregion
}
