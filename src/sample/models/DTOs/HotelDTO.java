package sample.models.DTOs;

import sample.models.people.Person;
import sample.models.viemModels.PersonForCreateHotelViewModel;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

public class HotelDTO {
    private String name;
    private String city;
    private int stars;
   private PersonForCreateHotelViewModel manager;
   private List<PersonForCreateHotelViewModel> receptionists;

    public HotelDTO() {
    }

    public HotelDTO(String name, String city, int stars, PersonForCreateHotelViewModel manager, List<PersonForCreateHotelViewModel> receptionists) {
        this.name = name;
        this.city = city;
        this.stars = stars;
        this.manager = manager;
        this.receptionists = receptionists;
    }

    public void setManager(PersonForCreateHotelViewModel manager) {
        this.manager = manager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public PersonForCreateHotelViewModel getManager() {
        return manager;
    }

    public List<PersonForCreateHotelViewModel> getReceptionists() {
        return receptionists;
    }

    public void setReceptionists(List<PersonForCreateHotelViewModel> receptionists) {
        this.receptionists = receptionists;
    }
}
