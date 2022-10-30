package sample.models.ratings;

import sample.models.people.Person;

import javax.persistence.*;

@Entity
public class ClientRating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Person manager;
    private double rating;

    public Long getId() {
        return id;
    }

    public Person getManager() {
        return manager;
    }

    public void setManager(Person manager) {
        this.manager = manager;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
