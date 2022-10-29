package sample.hotels;

import sample.people.Manager;
import sample.people.Owner;
import sample.reservations.Reservation;

import javax.persistence.*;
import java.util.List;
@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(mappedBy = "hotel")
    private Manager manager;
    @ManyToOne
    private Owner owner;
    @OneToMany(mappedBy = "hotel")
    private List<Reservation> reservations;
    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms;

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
