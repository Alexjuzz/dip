package di.model.entity.user;

import di.model.entity.user.telephone.Telephone;
import lombok.Data;

import java.util.List;

@Data
public class RegularUser extends User{
    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public List<Telephone> getTelephones() {
        return super.getTelephones();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public void setTelephones(List<Telephone> telephones) {
        super.setTelephones(telephones);
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }
}
