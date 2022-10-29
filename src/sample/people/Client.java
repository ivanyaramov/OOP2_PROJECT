package sample.people;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.List;
@Entity
public class Client extends Person{
    private double rating;
    @Column(nullable = false)
    private String telephone;
    @Column(nullable = false)
    private Gender gender;
    @Column(nullable = false)
    private int age;
    public Client() {
        super(Role.CLIENT);
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
