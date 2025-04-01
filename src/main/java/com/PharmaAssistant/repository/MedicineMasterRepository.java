package com.PharmaAssistant.repository;


import com.PharmaAssistant.Entity.MedicineMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineMasterRepository extends JpaRepository<MedicineMaster, String> {
    boolean existsByMedicineCode(String medicineCode);
}
