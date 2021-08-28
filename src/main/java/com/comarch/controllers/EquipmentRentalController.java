package com.comarch.controllers;


import com.comarch.models.Equipment;
import com.comarch.models.EquipmentRental;
import com.comarch.repositories.EquipmentRentalRepository;
import com.comarch.repositories.EquipmentRepository;
import com.comarch.requests.AddEquipmentRentalRequest;
import com.comarch.responses.RentalsForCustomersReponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("EquipmentRental")
public class EquipmentRentalController {

    @Autowired
    private EquipmentRentalRepository equipmentRentalRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;

    @PostMapping
    public EquipmentRental addEquipmentRental(@RequestBody AddEquipmentRentalRequest equipmentRentalRequest)
    {
        Equipment equipment = equipmentRepository.getOne(equipmentRentalRequest.equipment_id);
        EquipmentRental equipmentRental = new EquipmentRental();
        equipmentRental.setCustomerID(equipmentRentalRequest.customer_id);
        equipmentRental.setEquipmentID(equipmentRentalRequest.equipment_id);
        equipmentRental.setRentalStart(equipmentRentalRequest.rental_start);
        equipmentRental.setTimeInMinutes(equipmentRentalRequest.time_in_minutes);
        equipmentRental.setDateEndBasedOnRentalTime(equipmentRentalRequest.rental_start, equipmentRentalRequest.time_in_minutes);
        equipmentRental.setPriceEndBasedOnRentalTime(equipment.getPrice(), equipmentRentalRequest.time_in_minutes);

        return equipmentRentalRepository.saveAndFlush(equipmentRental);
    }

    @GetMapping("customer/{customerId}/date/{date}")
    public List<RentalsForCustomersReponse> getRentalsForCustomerForMonth(@PathVariable int customerId,  @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date)
    {
        List<EquipmentRental> rentals = equipmentRentalRepository.findAll()
                .stream()
                .filter(er -> er.getCustomerID() == customerId &&
                        (er.getRentalStart().getMonthValue() == date.getMonthValue() && er.getRentalStart().getYear() == date.getYear()))
                .collect(Collectors.toList());

        List<RentalsForCustomersReponse> rentalsForCustomers = new ArrayList<>();

        for(EquipmentRental rental : rentals)
        {
            RentalsForCustomersReponse rentalsForCustomersReponse = new RentalsForCustomersReponse();
            rentalsForCustomersReponse.customer_id = rental.getCustomerID();
            rentalsForCustomersReponse.equipment_id = rental.getEquipmentID();
            rentalsForCustomersReponse.price = rental.getPrice();
            rentalsForCustomersReponse.rental_start = rental.getRentalStart();
            rentalsForCustomersReponse.rental_end = rental.getRentalEnd();
            rentalsForCustomersReponse.time_in_minutes = rental.getTimeInMinutes();
            rentalsForCustomers.add(rentalsForCustomersReponse);
        }

        return rentalsForCustomers;
    }
}
