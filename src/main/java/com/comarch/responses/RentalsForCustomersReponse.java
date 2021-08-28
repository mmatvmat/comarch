package com.comarch.responses;

import com.sun.scenario.effect.Offset;

import java.time.OffsetDateTime;

public class RentalsForCustomersReponse {
    public int customer_id;
    public int equipment_id;
    public double price;
    public OffsetDateTime rental_start;
    public OffsetDateTime rental_end;
    public int time_in_minutes;
}
