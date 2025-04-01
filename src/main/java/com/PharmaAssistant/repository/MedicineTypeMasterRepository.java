package com.PharmaAssistant.repository;

import com.PharmaAssistant.Entity.MedicineTypeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineTypeMasterRepository extends JpaRepository<MedicineTypeMaster, String> {
}
