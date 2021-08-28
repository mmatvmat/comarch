package com.comarch.requests;

import java.time.OffsetDateTime;

public class AddEquipmentRentalRequest
{
    public int customer_id;
    public int equipment_id;
    public OffsetDateTime rental_start;
    public int time_in_minutes;
}
