package di.customexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TelephoneNotFoundException.class)
    public ResponseEntity<Object> handleTelephoneNotFoundException(TelephoneNotFoundException telephoneNotFoundException){
        Map<String, Object> body = new HashMap<>();
        body.put("message", telephoneNotFoundException.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BoatNotFoundExceptions.class)
    public ResponseEntity<Object> handleBoatNotFoundException(BoatNotFoundExceptions boatNotFoundExceptions){
        Map<String, Object> body = new HashMap<>();
        body.put("message",  boatNotFoundExceptions.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<Object> handleBookingNotFoundException(BookingNotFoundException bookingNotFoundException){
        Map<String, Object> body = new HashMap<>();
        body.put("message",  bookingNotFoundException.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
