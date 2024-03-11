package di.controller.user;

import di.model.dto.user.ResponseUser;
import di.model.entity.user.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public interface iUserController{

    @PostMapping("/createUser")
    public ResponseEntity<ResponseUser> createUser(@Valid @RequestBody User user);

    @GetMapping("/getAll")
    public  ResponseEntity<List<ResponseUser>> getAllUsers();

    @GetMapping("/getByPhone/{number}")
    public ResponseEntity<ResponseUser> getByNumber(@PathVariable("number") String number);

}
