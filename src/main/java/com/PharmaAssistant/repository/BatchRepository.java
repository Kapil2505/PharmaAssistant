package com.PharmaAssistant.repository;

import com.PharmaAssistant.Entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends JpaRepository<Batch, String> {
    boolean existsByBatchCode(String batchCode);
}
