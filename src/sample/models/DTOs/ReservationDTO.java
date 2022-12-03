package sample.models.DTOs;

import sample.models.hotels.Hotel;
import sample.models.hotels.Room;
import sample.models.people.Person;
import sample.models.reservations.ReservationType;

import javax.persistence.*;
import java.util.Date;

public class ReservationDTO {
    private ReservationType type;
    private Date dateOfArrival;
    private int days;
    private Room room;
    private Hotel hotel;

    public ReservationType getType() {
        return type;
    }

    public void setType(ReservationType type) {
        this.type = type;
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

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
