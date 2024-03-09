package di.model.entity.telephone;

import di.model.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Pattern;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Telephone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp="^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$", message = "The number invalid")
    private String number;

    public Telephone(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return  number;
    }

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
