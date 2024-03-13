package di.web.user;

import di.model.dto.user.ResponseUser;
import di.model.entity.user.RegularUser;
import di.model.entity.user.User;
import di.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WebUserService {
    private final UserRepository userRepository;

    @Autowired
    public WebUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public ResponseUser registerUser(ResponseUser responseUser){
        User user = convertResponseUserToUser(responseUser);
         userRepository.save(user);
        return responseUser;
    }

    public boolean authenticate(String username, String email,String password){
        Optional<User> user = userRepository.getByUserName(username);
        if(user.isPresent()){
            ResponseUser responseUser = convertUserToResponseUser(user.get());
            System.out.println(responseUser.getPassword() +  " "  + responseUser.getName());
            return (responseUser.getPassword().equals(password) & responseUser.getEmail().equals(email));
        }
        return false;
    }



    private ResponseUser convertUserToResponseUser(User user) {
        ResponseUser response = new ResponseUser();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPassword(user.getPassword());
        response.setTelephone(user.getTelephone());
        return response;
    }



    public User convertResponseUserToUser(ResponseUser responseUser) {
        User user = new User();
        user.setEmail(responseUser.getEmail());
        user.setPassword(responseUser.getPassword());
        user.setTelephone(responseUser.getTelephone());
        user.setName(responseUser.getName());
        return user;
    }

}
