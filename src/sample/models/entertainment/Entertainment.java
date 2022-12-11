package sample.models.entertainment;

import sample.models.hotels.Hotel;
import sample.models.reservations.Reservation;

import javax.persistence.*;
import java.util.List;

@Entity
public class Entertainment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column(nullable = false)
    private double price;
    @ManyToMany
    private List<Reservation> reservations;
    @ManyToOne
    private Hotel hotel;

    public Entertainment() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
