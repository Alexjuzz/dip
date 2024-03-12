package di.controller.user;

import di.customexceptions.user.UserEmptyResultDataException;
import di.customexceptions.user.UserNotFoundException;
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
public class UserController implements iUserController {
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
        try {
            List<ResponseUser> responseUsers = service.getAllUsers();
            if (responseUsers.isEmpty()) {
                throw new UserEmptyResultDataException("Users not found");
            }
            return ResponseEntity.ok(responseUsers);
        } catch (UserEmptyResultDataException ex) {
            throw new UserEmptyResultDataException(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity<ResponseUser> getByNumber(String number) {
        try {
            ResponseUser responseUser = service.getUserByPhone(number);
            if (responseUser == null) {
                throw new UserNotFoundException("User not found");
            }
            return ResponseEntity.ok(responseUser);
        } catch (UserNotFoundException ex) {
            throw new UserNotFoundException(ex.getMessage());
        }
    }
}
