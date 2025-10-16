package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;

    public Product() {}

    public void setName(String s) {
    }

    public void setPrice(double v) {
    }

    public String getName() {
        return "";
    }

    // Getters et Setters
}