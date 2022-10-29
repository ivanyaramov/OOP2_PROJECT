package sample.people;

import javax.persistence.*;

@Entity
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Role role;
    @Column(nullable = false)
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
