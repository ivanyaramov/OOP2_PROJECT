package sample.people;

import javax.persistence.Entity;

@Entity
public class Administrator extends Person{

    public Administrator() {
        super(Role.ADMIN);
    }
}
