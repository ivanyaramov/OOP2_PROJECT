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
    @OneToMany
    private List<Person> manager;
    @ManyToOne
    private Person owner;
    @OneToMany
    private List<Person> receptionists;
    @OneToMany(mappedBy = "hotel")
    private List<Reservation> reservations;
    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms;

    public List<Person> getManager() {
        return manager;
    }

    public void setManager(List<Person> manager) {
        this.manager = manager;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public List<Person> getReceptionists() {
        return receptionists;
    }

    public void setReceptionists(List<Person> receptionists) {
        this.receptionists = receptionists;
    }
}
