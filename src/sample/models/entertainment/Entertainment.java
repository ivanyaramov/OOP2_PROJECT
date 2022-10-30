package sample.models.entertainment;

import javax.persistence.*;

@Entity
public class Entertainment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(30)")
    private EntertainmentType type;
    @Column(nullable = false)
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
