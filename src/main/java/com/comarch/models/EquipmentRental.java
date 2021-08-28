package com.comarch.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity(name = "equipment_rental")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EquipmentRental
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int customer_id;
    private int equipment_id;
    private OffsetDateTime rental_start;
    private OffsetDateTime rental_end;
    private double price;
    private boolean is_paid;
    private int time_in_minutes;

    public int getID() {
        return id;
    }

    public void setID(int ID) {
        id = ID;
    }

    public int getCustomerID() {
        return customer_id;
    }

    public void setCustomerID(int customerID) {
        customer_id = customerID;
    }

    public int getEquipmentID() {
        return equipment_id;
    }

    public void setEquipmentID(int equipmentID) {
        equipment_id = equipmentID;
    }

    public OffsetDateTime getRentalStart() {
        return rental_start;
    }

    public void setRentalStart(OffsetDateTime dateAdd) {
        rental_start = dateAdd;
    }

    public OffsetDateTime getRentalEnd() {
        return rental_end;
    }

    public void setRentalEnd(OffsetDateTime dateEnd) {
        rental_end = dateEnd;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        price = price;
    }

    public void setPriceEndBasedOnRentalTime(double price, int time_in_minutes)
    {
        this.price = price * time_in_minutes;
    }

    public void setDateEndBasedOnRentalTime(OffsetDateTime rental_start, int time_in_minutes)
    {
        this.rental_end = rental_start.plusMinutes(time_in_minutes);
    }

    public boolean getIsPaid()
    {
        return is_paid;
    }

    public void setIsPaid(boolean is_paid) {
        this.is_paid = is_paid;
    }

    public int getTimeInMinutes()
    {
        return time_in_minutes;
    }

    public void setTimeInMinutes(int time_in_minutes)
    {
        this.time_in_minutes = time_in_minutes;
    }
}
