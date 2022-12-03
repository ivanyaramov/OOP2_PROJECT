package sample.models.viemModels;

import sample.models.hotels.Hotel;
import sample.models.hotels.Room;
import sample.models.hotels.RoomCategory;
import sample.models.people.Person;
import sample.models.reservations.ReservationType;

import javax.persistence.*;
import java.util.Date;

public class ReservationViewModel {
    private ReservationType type;
    private Date dateOfArrival;
    private int days;
    private Long roomId;
    private RoomCategory roomCategory;
    private String hotelName;
    private boolean ended;
    private boolean nearlyEnded;
    private boolean risky;
    private double price;

    public ReservationViewModel() {
    }

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

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public RoomCategory getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(RoomCategory roomCategory) {
        this.roomCategory = roomCategory;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public boolean isEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }

    public boolean isNearlyEnded() {
        return nearlyEnded;
    }

    public void setNearlyEnded(boolean nearlyEnded) {
        this.nearlyEnded = nearlyEnded;
    }

    public boolean isRisky() {
        return risky;
    }

    public void setRisky(boolean risky) {
        this.risky = risky;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
