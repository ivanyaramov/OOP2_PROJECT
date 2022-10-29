package sample.people;

import sample.hotels.Hotel;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;
@Entity
public class Manager extends Person{
    @OneToMany
    private List<Receptionist> receptionists;
    @OneToOne
    private Hotel hotel;
    public Manager() {
        super(Role.MANAGER);
    }
}
