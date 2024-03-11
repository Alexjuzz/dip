package di.service.user;


import di.model.dto.user.ResponseUser;
import di.model.entity.user.RegularUser;
import di.model.entity.user.User;
import di.model.entity.telephone.Telephone;
import di.repository.telephone.TelephoneRepository;
import di.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;
    private final TelephoneRepository telephoneRepository;
    @Autowired
    public UserService(UserRepository repository, TelephoneRepository telephoneRepository) {
        this.repository = repository;
        this.telephoneRepository = telephoneRepository;
    }


    @Transactional
    public ResponseUser createUser(User user) {
        User requestUser = new User();
        Telephone telephone = new Telephone(user.getTelephone().getNumber());
        telephone.setUser(requestUser);
        telephone.setNumber(user.getTelephone().getNumber());

        requestUser.setTelephone(telephone);
        requestUser.setName(user.getName());
        requestUser.setEmail(user.getEmail());
        requestUser.setPassword(user.getPassword());

        return convertUserToResponseUser(repository.save(requestUser));
    }


    //region методы конвертации обьектов.
    private ResponseUser convertUserToResponseUser(User user) {
        ResponseUser response = new ResponseUser();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPassword(user.getPassword());
        response.setTelephone(user.getTelephone());
        return response;
    }

    public List<ResponseUser> getAllUsers() {
        return repository.findAll().stream().map(this::convertUserToResponseUser).toList();
    }


    private User convertResponseUserToUser(ResponseUser responseUser) {
        User user = new RegularUser();
        user.setEmail(responseUser.getEmail());
        user.setPassword(responseUser.getPassword());
        user.setId(responseUser.getId());
        user.setTelephone(responseUser.getTelephone());
        user.setName(user.getName());
        return user;
    }


}
