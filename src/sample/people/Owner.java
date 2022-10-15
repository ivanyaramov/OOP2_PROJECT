package sample.people;

import sample.hotels.Hotel;

import java.util.List;

public class Owner extends Person{
    private List<Hotel> hotels;
    public Owner() {
        super(Role.OWNER);

    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }
}
