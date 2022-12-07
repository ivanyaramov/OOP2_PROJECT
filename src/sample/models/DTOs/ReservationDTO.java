package sample.models.DTOs;

import sample.models.reservations.ReservationType;

import java.util.Date;
import java.util.List;

public class ReservationDTO {
    private ReservationType type;
    private Date dateOfArrival;
    private int days;
    private Long room;
    private Long hotel;
    private List<Long> entertainmentIds;

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

    public Long getRoom() {
        return room;
    }

    public void setRoom(Long room) {
        this.room = room;
    }

    public Long getHotel() {
        return hotel;
    }

    public void setHotel(Long hotel) {
        this.hotel = hotel;
    }

    public List<Long> getEntertainmentIds() {
        return entertainmentIds;
    }

    public void setEntertainmentIds(List<Long> entertainmentIds) {
        this.entertainmentIds = entertainmentIds;
    }
}
