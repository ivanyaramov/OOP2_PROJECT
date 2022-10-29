package sample.people;

import sample.hotels.Hotel;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
@Entity
public class Owner extends Person{
    @OneToMany(mappedBy = "owner")
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
