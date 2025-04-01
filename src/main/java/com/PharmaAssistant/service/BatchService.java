package com.PharmaAssistant.service;


import com.PharmaAssistant.payload.BatchDto;
import com.PharmaAssistant.payload.BatchResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BatchService {
    BatchResponseDto addBatch(BatchDto batchDto);
    BatchResponseDto getBatchById(String batchCode);
    Page<BatchResponseDto> getAllBatches(Pageable pageable);
}
