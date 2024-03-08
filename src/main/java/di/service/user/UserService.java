package di.service.user;


import di.model.dto.user.ResponseUser;
import di.model.entity.user.RegularUser;
import di.model.entity.user.User;
import di.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }
//
//    @Transactional
//    public ResponseUser createUser(){
//        return null;
//    }


    //region методы конвертации обьектов.
    private ResponseUser convertUserToResponseUser(User user){
        ResponseUser response = new ResponseUser();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPassword(user.getPassword());
        response.setTelephones(user.getTelephones());
        return response;
    }

    private User convertResponseUserToUser(ResponseUser responseUser){
        User user = new RegularUser();
        user.setEmail(responseUser.getEmail());
        user.setPassword(responseUser.getPassword());
        user.setId(responseUser.getId());
        user.setTelephones(responseUser.getTelephones());
        user.setName(user.getName());
        return user;
    }
}
