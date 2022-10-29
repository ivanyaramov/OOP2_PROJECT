package sample.people;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Role role;
    private String fullName;

    public Person(Role role) {
        this.role = role;
    }

    public Person() {

    }

    public Role getRole() {
        return role;
    }

}
