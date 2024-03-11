package di.controller.user;

import di.model.dto.user.ResponseUser;
import di.model.entity.user.User;
import di.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Controller
public class UserController implements iUserController{
        private final UserService service;

        @Autowired
    public UserController(UserService service) {
        this.service = service;
    }


    @Override
    public ResponseEntity<ResponseUser> createUser(@Valid User user) {
       return ResponseEntity.ok(service.createUser(user));
    }

    @Override
    public ResponseEntity<List<ResponseUser>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @Override
    public ResponseEntity<ResponseUser> getByNumber(String number) {
        return ResponseEntity.ok(service.getUserByPhone(number));
    }

}
