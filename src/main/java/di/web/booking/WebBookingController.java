package di.web.booking;

import di.customexceptions.user.UserNotFoundException;
import di.enums.BookingTime;
import di.model.dto.booking.ResponseBooking;
import di.model.dto.seat.ResponseSeat;
import di.model.dto.user.LoginUser;
import di.model.dto.user.ResponseUser;
import di.model.entity.booking.Booking;
import di.model.entity.seats.Seat;
import di.model.entity.user.User;
import di.repository.user.UserRepository;
import di.web.user.WebUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class WebBookingController {

    private final WebUserService service;
    private final WebBookingService bookingService;

    @Autowired
    public WebBookingController(WebUserService service, WebBookingService bookingService) {
        this.service = service;
        this.bookingService = bookingService;
    }

    @GetMapping("/booking/process-booking")
    public String processSelection(@RequestParam Long seatId, @RequestParam String time, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("seatId", seatId);
        redirectAttributes.addAttribute("time", time);

        return "redirect:/booking/form";
    }

    @PostMapping("/booking/{seatId}")
    public String setBookingToSeat(@RequestParam Long seatId, @RequestParam("time") String time, String name, String email, String phone, Model model) {
        User user = service.findByPhone(phone);
        if (user == null) {
            throw new UserNotFoundException("user not found");
        }
        ResponseBooking responseBooking = bookingService.makeBooking(seatId, time, user);
        model.addAttribute("booking", responseBooking);
        model.addAttribute("telephone", phone);
        model.addAttribute("time", time);
        return "booking";
    }
    @GetMapping("/booking/form")
    public String showBookingForm(@RequestParam("seatId") Long seatId, @RequestParam("time") String time, Model model) {
        model.addAttribute("seatId", seatId);
        model.addAttribute("time", time);
        return "makeBooking";
    }
//    @GetMapping("/boats/process-selection")
//    public String processSelection(@RequestParam("boatId") Long boatId, @RequestParam("time") String time, Model model, RedirectAttributes redirectAttributes) {
//        // Передача id корабля и выбранного времени в сервис, который возвращает список доступных мест
//        List<ResponseSeat> availableSeats = bookingService.getAvailableSeats(boatId, time);
//
//        // Передача полученных данных в модель для отображения на странице деталей корабля
//        model.addAttribute("availableSeats", availableSeats);
//        model.addAttribute("selectedTime", time);
//
//        // Переадресация на страницу с деталями корабля и доступными местами
//        return "boatDetails";
//    }
}
