package sample.ratings;

import sample.people.Manager;

import javax.persistence.*;

@Entity
public class ClientRating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Manager manager;
    private double rating;

    public Long getId() {
        return id;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
