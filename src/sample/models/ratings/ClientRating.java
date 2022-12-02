package sample.models.ratings;

import sample.models.people.Person;

import javax.persistence.*;

@Entity
public class ClientRating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Person client;
    private double rating;

    public ClientRating(Person client, double rating) {
        this.client = client;
        this.rating = rating;
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
}
