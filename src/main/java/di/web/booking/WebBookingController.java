package di.web.booking;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebBookingController {

    @PostMapping("/booking/make")
    public String makeBooking(){

    }
}
