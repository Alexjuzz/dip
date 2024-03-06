package di.model.entity.boats;


import di.enums.TripType;
import di.model.entity.seats.Seat;
import di.model.entity.trips.Trip;
import di.model.interfaces.InterfaceShip;
import di.model.interfaces.InterfaceTrip;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Абстрактный класс кораблей.
 */
@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "boat_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "boat")
public class AbstractBoat implements InterfaceShip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "boat_name")
    private String name;

    @Column(name = "trip_type")
    @Enumerated(EnumType.STRING)
    private TripType tripType;

    /**
     * Места - у каждого корабля есть свои места(сущность место) которые хранятся в листе
     * Связь один ко многим
     */
    @OneToMany(mappedBy = "boat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private  List<Seat> places = new CopyOnWriteArrayList<>();

    /**
     * Тур - у каждого судна может быть только один выбраный в текущий момент тур.
     * Но при не обходимости его можео будет поменять.
     * В зависимсотри какой тур выбран будет создан нужный обьект тура и будет выбрана нужная логика записи.
     */

    @OneToOne(mappedBy = "boat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Trip trip;


    /**
     * Конструктор без параметров, сделан для того чтобы сразу создать свободные места и записать
     * их в лист places. Также проинициализировать поля seat's.
     */
    public AbstractBoat() {
        for (int i = 0; i < 30; i++) {
            Seat seat = new Seat();
            seat.setSet_number(i);
            seat.setIsOccupied(true);
            seat.setBoat(this);
            this.places.add(seat);
        }
    }

    /**
     *  Конструктор для создания нового судна, с инициализацией всех мест и имени судна.
     * @param name - имя судна
     * @param capacity - количество мест
     */
    protected AbstractBoat(String name, int capacity) {
        for (int i = 0; i < capacity; i++) {
            this.name = name;
            Seat seat = new Seat();
            seat.setSet_number(i);
            seat.setIsOccupied(true);
            seat.setBoat(this);
            this.places.add(seat);
        }
    }


}




