package di.model.entity.user;

import di.model.entity.telephone.Telephone;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//TODO : Подумать о Security - На счет USERDETAILS .
@Data
@Entity
@Table(name = "user")
public abstract class User  {
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.telephones = telephones;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",nullable = false,length = 40)
    private String name;

    @Column(name = "email",nullable = false,length = 250)
    private String email;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Telephone> telephones = new ArrayList<>();

    @Column(name = "password",nullable = false)
    private String password;
}
