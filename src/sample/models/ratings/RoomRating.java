package sample.models.ratings;

import sample.models.hotels.Room;
import sample.models.people.Person;

import javax.persistence.*;

@Entity
public class RoomRating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Person client;
    @ManyToOne
    private Room room;
    private double rating;

    public RoomRating(Person client, Room room, double rating) {
        this.client = client;
        this.room = room;
        this.rating = rating;
    }

    public RoomRating() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getClient() {
        return client;
    }

    public void setClient(Person client) {
        this.client = client;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
