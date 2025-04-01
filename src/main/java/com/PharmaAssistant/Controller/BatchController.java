package com.PharmaAssistant.Controller;


import com.PharmaAssistant.payload.BatchDto;
import com.PharmaAssistant.payload.BatchResponseDto;
import com.PharmaAssistant.service.BatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/batches")
public class BatchController {

    private static final Logger logger = LoggerFactory.getLogger(BatchController.class);

    @Autowired
    private BatchService batchService;

    @PostMapping
    public ResponseEntity<BatchResponseDto> addBatch(@RequestBody BatchDto batchDto) {
        logger.info("Received request to add new batch with batchCode: {}", batchDto.getBatchCode());
        try {
            BatchResponseDto response = batchService.addBatch(batchDto);
            logger.info("Successfully processed batch addition for batchCode: {}", batchDto.getBatchCode());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error processing batch addition: {}", e.getMessage());
            throw e;
        }
    }

    @GetMapping("/{batchCode}")
    public ResponseEntity<BatchResponseDto> getBatchById(@PathVariable String batchCode) {
        logger.info("Received request to get batch by id: {}", batchCode);
        try {
            BatchResponseDto response = batchService.getBatchById(batchCode);
            logger.info("Successfully retrieved batch with code: {}", batchCode);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error retrieving batch: {}", e.getMessage());
            throw e;
        }
    }

    @GetMapping
    public ResponseEntity<Page<BatchResponseDto>> getAllBatches(
            @PageableDefault(size = 10) Pageable pageable) {
        logger.info("Received request to get all batches with pagination");
        try {
            Page<BatchResponseDto> response = batchService.getAllBatches(pageable);
            logger.info("Successfully retrieved {} batches", response.getTotalElements());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error retrieving batches: {}", e.getMessage());
            throw e;
        }
    }
}