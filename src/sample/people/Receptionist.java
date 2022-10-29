package sample.people;

import sample.hotels.Hotel;
import sample.reservations.Reservation;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
@Entity
public class Receptionist extends Person{
    @OneToMany(mappedBy = "receptionist")
    private List<Reservation> reservations;
    @ManyToOne
    private Hotel hotel;
    public Receptionist() {
        super(Role.RECEPTIONIST);
    }
}
