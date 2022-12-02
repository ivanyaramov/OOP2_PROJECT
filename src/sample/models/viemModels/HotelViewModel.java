package sample.models.viemModels;

public class HotelViewModel {
    private String name;
    private String city;
    private int stars;

    public HotelViewModel(String name, String city, int stars) {
        this.name = name;
        this.city = city;
        this.stars = stars;
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
}
