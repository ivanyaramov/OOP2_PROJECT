package sample.reservations;

import sample.hotels.Hotel;
import sample.hotels.Room;
import sample.hotels.RoomCategory;
import sample.people.Person;

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
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(30)", nullable = false)
    private ReservationEndType endType;
    @Column(nullable = false)
    private Date dateOfArrival;
    @Column(nullable = false)
    private Date dateOfDeparture;
    @ManyToOne
    private Room room;
    @ManyToOne
    private Hotel hotel;
    @ManyToOne
    private Person client;
    @ManyToOne
    private Person receptionist;

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

    public ReservationEndType getEndType() {
        return endType;
    }

    public void setEndType(ReservationEndType endType) {
        this.endType = endType;
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

    public Date getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(Date dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
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

    public Person getReceptionist() {
        return receptionist;
    }

    public void setReceptionist(Person receptionist) {
        this.receptionist = receptionist;
    }
}
