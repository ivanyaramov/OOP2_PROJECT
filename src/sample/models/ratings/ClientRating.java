package sample.models.ratings;

import sample.models.people.Person;
import sample.models.reservations.Reservation;

import javax.persistence.*;

@Entity
public class ClientRating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Person client;
    @OneToOne
    private Reservation reservation;
    private double rating;

    public ClientRating(Person client, double rating, Reservation reservation) {
        this.client = client;
        this.rating = rating;
        this.reservation = reservation;
    }

    public ClientRating() {
    }

    public Long getId() {
        return id;
    }

    public Person getClient() {
        return client;
    }

    public void setClient(Person manager) {
        this.client = manager;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
