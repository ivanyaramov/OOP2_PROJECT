package sample.models.reservations;

import sample.models.hotels.Hotel;
import sample.models.hotels.Room;
import sample.models.hotels.RoomCategory;
import sample.models.people.Person;

import javax.persistence.*;
import java.util.Date;
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(30)", nullable = false)
    private ReservationType type;
    @Column(nullable = false)
    private Date dateOfArrival;
    @Column(nullable = false)
    private int days;
    @Column
    private boolean ended;
    @ManyToOne
    private Room room;
    @ManyToOne
    private Hotel hotel;
    @ManyToOne
    private Person client;


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

    public ReservationType getType() {
        return type;
    }

    public void setType(ReservationType type) {
        this.type = type;
    }

    public RoomCategory getRoomCategory() {
        return room.getRoomCategory();
    }

    public Date getDateOfArrival() {
        return dateOfArrival;
    }

    public void setDateOfArrival(Date dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Person getClient() {
        return client;
    }

    public void setClient(Person client) {
        this.client = client;
    }

    public boolean isEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }
}
