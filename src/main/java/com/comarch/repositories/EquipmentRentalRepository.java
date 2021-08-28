package com.comarch.repositories;

import com.comarch.models.EquipmentRental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRentalRepository extends JpaRepository<EquipmentRental, Integer>
{
}
