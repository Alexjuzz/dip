package di.model.dto.user;

import di.model.entity.user.telephone.Telephone;
import lombok.Data;

import java.util.List;

@Data
public class ResponseUser {
    private Long id;
    private String name;
    private String email;
    private List<Telephone> telephones;
    private String password;
}
