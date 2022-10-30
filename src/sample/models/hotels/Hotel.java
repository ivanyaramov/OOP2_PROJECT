package sample.models.hotels;

import sample.models.people.Person;
import sample.models.reservations.Reservation;

import javax.persistence.*;
import java.util.List;
@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Person manager;
    @ManyToOne
    private Person owner;
    @OneToMany(mappedBy = "hotel")
    private List<Reservation> reservations;
    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms;

    public Person getManager() {
        return manager;
    }

    public void setManager(Person manager) {
        this.manager = manager;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
