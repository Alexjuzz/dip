package di.model.dto.user;

import di.model.entity.client.telephone.Telephone;
import lombok.Data;

import java.util.List;

@Data
public class ResponseUser {
    private Long id;
    private String name;
    private String email;
    private List<Telephone> telephoneList;
    private String password;
}
