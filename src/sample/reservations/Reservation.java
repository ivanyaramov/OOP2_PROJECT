package sample.reservations;

import sample.hotels.Hotel;
import sample.hotels.Room;
import sample.hotels.RoomCategory;
import sample.people.Client;
import sample.people.Receptionist;

import javax.persistence.*;
import java.util.Date;
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private ReservationType type;
    private ReservationEndType endType;
    private Date dateOfArrival;
    private Date dateOfDeparture;
    @ManyToOne
    private Room room;
    @ManyToOne
    private Hotel hotel;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Receptionist receptionist;

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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Receptionist getReceptionist() {
        return receptionist;
    }

    public void setReceptionist(Receptionist receptionist) {
        this.receptionist = receptionist;
    }
}
