package com.PharmaAssistant.repository;

import com.PharmaAssistant.Entity.ShippingMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShippingMasterRepository extends JpaRepository<ShippingMaster, String> {
    Optional<ShippingMaster> findByMedicineTypeCodeAndWeightRange(String medicineTypeCode, String weightRange);
}