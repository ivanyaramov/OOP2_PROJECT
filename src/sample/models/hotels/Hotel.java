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
    private String name;
    private String city;
    private int stars;
    @OneToOne
    private Person manager;
    @ManyToOne
    private Person owner;
    @OneToMany
    private List<Person> receptionists;
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

    public List<Person> getReceptionists() {
        return receptionists;
    }

    public void setReceptionists(List<Person> receptionists) {
        this.receptionists = receptionists;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
