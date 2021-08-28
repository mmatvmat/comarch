package com.comarch.controllers;

import com.comarch.models.Equipment;
import com.comarch.repositories.EquipmentRepository;
import com.comarch.requests.AddNewEquipmentRequest;
import com.comarch.requests.UpdateEquipmentPriceRequest;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.Date;

@RestController
@RequestMapping("Equipment")
public class EquipmentController
{
    @Autowired
    private EquipmentRepository equipmentRepository;

    @PostMapping
    public Equipment createEquipment(@RequestBody AddNewEquipmentRequest equipmentRequest)
    {
        Equipment equipment = new Equipment();
        equipment.setDateAdded(OffsetDateTime.now());
        equipment.setPrice(equipmentRequest.price_per_minute);
        equipment.setName(equipmentRequest.name);
        return equipmentRepository.saveAndFlush(equipment);
    }

    @PutMapping
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Equipment updateEquipmentPrice(@PathVariable Integer id, @RequestBody UpdateEquipmentPriceRequest priceUpdate) throws NotFoundException
    {
        // Nie byłam pewna czy aktualizacja ceny powinna być dla danego typu produktu czy tylko dla 1 określonego produktu
        //ponieważ odkurzacze mogą różnić się modelami i mieć inną cenę za minutę użytkowania
        // Jeśli cena byłaby określana na postawie typu produktu wtedy dodałabym tabelę zawierającą typ produktu, skrócony kod oraz cenę a tabeli
        //equipment byłaby kolumna kodem typu produktu
        if(!equipmentRepository.existsById(id))
        {
            throw new NotFoundException("Object does not exists");
        }
        Equipment equipmentForUpdate = equipmentRepository.getOne(id);
        equipmentForUpdate.setPrice(priceUpdate.price);
        return equipmentRepository.saveAndFlush(equipmentForUpdate);
    }
}
