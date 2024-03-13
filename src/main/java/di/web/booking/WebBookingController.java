package di.web.booking;

import di.enums.BookingTime;
import di.model.dto.booking.ResponseBooking;
import di.model.dto.user.LoginUser;
import di.model.dto.user.ResponseUser;
import di.model.entity.booking.Booking;
import di.model.entity.user.User;
import di.repository.user.UserRepository;
import di.web.user.WebUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebBookingController {

    private final WebUserService service;
    private final WebBookingService bookingService;

    @Autowired
    public WebBookingController(WebUserService service,WebBookingService bookingService) {
        this.service = service;
        this.bookingService = bookingService;
    }

    @PostMapping("/booking/make")
    public String makeBooking(@RequestParam("phone") String phone,
                              @RequestParam("seatId") Long seatId,
                              @RequestParam("time") String time,
                              Model model) {
        // Проверка наличия пользователя в базе
        User user = service.findByPhone(phone);
        if (user == null) {
            model.addAttribute("errorMessage", "Пользователь не найден. Пожалуйста, зарегистрируйтесь.");
            return "booking-form";
        }

        ResponseBooking booking = bookingService.makeBooking(seatId, time, user);

        // Добавление информации о бронировании в модель для отображения на странице подтверждения
        model.addAttribute("booking", booking);
        return "booking-confirmation"; // Имя шаблона страницы подтверждения бронирования
    }

}
