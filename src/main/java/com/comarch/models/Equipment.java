package com.comarch.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Date;

@Entity(name = "equipment")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Equipment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price_per_minute;
    private OffsetDateTime date_added;

    public int getID() {
        return id;
    }

    public void setID(int ID) {
        this.id = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price_per_minute;
    }

    public void setPrice(double price) {
        price_per_minute = price;
    }

    public OffsetDateTime getDateAdded() {
        return date_added;
    }

    public void setDateAdded(OffsetDateTime dateAdded) {
        date_added = dateAdded;
    }
}
