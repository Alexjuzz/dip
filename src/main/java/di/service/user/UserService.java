package di.service.user;


import di.model.dto.user.ResponseUser;
import di.model.entity.user.RegularUser;
import di.model.entity.user.User;
import di.model.entity.telephone.Telephone;
import di.repository.telephone.TelephoneRepository;
import di.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository repository;
    private final TelephoneRepository telephoneRepository;

    @Autowired
    private Validator validator;
    @Autowired
    public UserService(UserRepository repository, TelephoneRepository telephoneRepository) {
        this.repository = repository;
        this.telephoneRepository = telephoneRepository;
    }


    @Transactional
    public ResponseUser createUser(User user) {
        //TODO доделать validator
        Set<ConstraintViolation<Telephone>> validation = validator.validate(user.getTelephone());
        if(!validation.isEmpty()){
            throw new ConstraintViolationException(validation);
        }

        if(telephoneRepository.existsByNumber(user.getTelephone().getNumber())){
            throw  new IllegalArgumentException("Telephone number already exist");
        }
        User requestUser = new User();
        requestUser.setName(user.getName());
        requestUser.setEmail(user.getEmail());
        requestUser.setPassword(user.getPassword());

        Telephone telephone = new Telephone(user.getTelephone().getNumber());
        telephone.setUser(requestUser);
        requestUser.setTelephone(telephone);

        return convertUserToResponseUser(repository.save(requestUser));
    }
    @Transactional
    public List<ResponseUser> getAllUsers() {
        return repository.findAll().stream().map(this::convertUserToResponseUser).toList();
    }

    @Transactional
    public ResponseUser getUserByPhone(String number){
        User user = repository.getByTelephone(number);
        return convertUserToResponseUser(user);
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



    private User convertResponseUserToUser(ResponseUser responseUser) {
        User user = new RegularUser();
        user.setEmail(responseUser.getEmail());
        user.setPassword(responseUser.getPassword());
        user.setId(responseUser.getId());
        user.setTelephone(responseUser.getTelephone());
        user.setName(user.getName());
        return user;
    }

    //endregion

}
