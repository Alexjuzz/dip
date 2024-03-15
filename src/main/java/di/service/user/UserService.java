package di.service.user;


import di.customexceptions.telephone.TelephoneAlreadyExistException;
import di.customexceptions.user.UserEmptyResultDataException;
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
    /**
     * Конструктор для создания экземпляра UserService.
     *
     * @param repository          Репозиторий пользователей.
     * @param telephoneRepository Репозиторий телефонов.
     */
    @Autowired
    public UserService(UserRepository repository, TelephoneRepository telephoneRepository) {
        this.repository = repository;
        this.telephoneRepository = telephoneRepository;
    }

    /**
     * Метод для создания нового пользователя.
     *
     * @param user Пользователь для создания.
     * @return Ответ с информацией о созданном пользователе.
     * @throws ConstraintViolationException Если есть нарушения ограничений.
     * @throws TelephoneAlreadyExistException Если номер телефона уже существует.
     */
    @Transactional
    public ResponseUser createUser(User user) {
        //TODO доделать validator
        Set<ConstraintViolation<Telephone>> validation = validator.validate(user.getTelephone());
        if(!validation.isEmpty()){
            throw new ConstraintViolationException(validation);
        }

        if(telephoneRepository.existsByNumber(user.getTelephone().getNumber())){
            throw  new TelephoneAlreadyExistException("Telephone number already exist");
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
    /**
     * Метод для получения всех пользователей.
     *
     * @return Список ответов с информацией о пользователях.
     */
    @Transactional
    public List<ResponseUser> getAllUsers() {
        return repository.findAll().stream().map(this::convertUserToResponseUser).toList();
    }

    /**
     * Метод для получения пользователя по номеру телефона.
     *
     * @param number Номер телефона пользователя.
     * @return Ответ с информацией о найденном пользователе.
     */
    @Transactional
    public ResponseUser getUserByPhone(String number){
        User user = repository.getByTelephone(number);
        return convertUserToResponseUser(user);
    }

    /**
     * Преобразование объекта пользователя в ответ о пользователе.
     *
     * @param user Пользователь для преобразования.
     * @return Ответ о пользователе.
     */

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

    /**
     * Преобразование объекта ответа о пользователе в пользователя.
     *
     * @param responseUser Ответ о пользователе для преобразования.
     * @return Пользователь.
     */

    public User convertResponseUserToUser(ResponseUser responseUser) {
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
