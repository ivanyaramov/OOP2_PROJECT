package sample.models.DTOs;

import javax.persistence.Column;

public class EntertainmentDTO {
    private String name;
    private double price;

    public EntertainmentDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
