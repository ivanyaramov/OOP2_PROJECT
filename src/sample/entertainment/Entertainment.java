package sample.entertainment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Entertainment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private EntertainmentType type;
    private double price;

    public EntertainmentType getType() {
        return type;
    }

    public void setType(EntertainmentType type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
