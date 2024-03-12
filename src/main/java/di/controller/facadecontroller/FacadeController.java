package di.controller.facadecontroller;


import di.controller.boat.BoatController;
import di.controller.booking.BookingController;
import di.service.boat.BoatService;
import di.service.booking.BookingService;
import di.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FacadeController {
    private final BookingService bookingService;
    private final UserService userService;

        @Autowired

    public FacadeController(BookingService bookingService, UserService userService) {
        this.bookingService = bookingService;
        this.userService = userService;
    }



}
