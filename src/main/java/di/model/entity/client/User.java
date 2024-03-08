package di.model.entity.client;

import di.model.entity.client.telephone.Telephone;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.ArrayList;
import java.util.List;

@Data

@Entity
@Table(name = "user")
public abstract class User extends SecurityProperties.User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",nullable = false,length = 40)
    private String name;

    @Column(name = "email",nullable = false,length = 250)
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Telephone> telephones = new ArrayList<>();

    @Column(name = "password",nullable = false)
    private String password;
}
