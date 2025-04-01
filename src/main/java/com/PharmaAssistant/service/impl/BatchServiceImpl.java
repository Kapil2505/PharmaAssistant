package com.PharmaAssistant.service.impl;

import com.PharmaAssistant.Entity.Batch;
import com.PharmaAssistant.Entity.ShippingMaster;
import com.PharmaAssistant.exception.PharmaBusinessException;
import com.PharmaAssistant.exception.PharmaException;
import com.PharmaAssistant.payload.BatchDto;
import com.PharmaAssistant.payload.BatchResponseDto;
import com.PharmaAssistant.repository.BatchRepository;
import com.PharmaAssistant.repository.MedicineMasterRepository;
import com.PharmaAssistant.repository.ShippingMasterRepository;
import com.PharmaAssistant.service.BatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class BatchServiceImpl implements BatchService {

    private static final Logger logger = LoggerFactory.getLogger(BatchServiceImpl.class);

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private MedicineMasterRepository medicineMasterRepository;

    @Autowired
    private ShippingMasterRepository shippingMasterRepository;

    @Override
    public BatchResponseDto addBatch(BatchDto batchDto) {
        logger.info("Entering addBatch method with batchDto: {}", batchDto);

        // Validate input
        validateBatch(batchDto);

        // Calculate shipping charge and care level
        BigDecimal shippingCharge = calculateShippingCharge(batchDto);
        String careLevel = determineCareLevel(batchDto.getMedicineTypeCode());

        // Create and save batch
        Batch batch = new Batch();
        batch.setBatchCode(batchDto.getBatchCode());
        batch.setMedicineCode(batchDto.getMedicineCode());
        batch.setWeight(batchDto.getWeight());
        batch.setPrice(batchDto.getPrice());
        batch.setMedicineTypeCode(batchDto.getMedicineTypeCode());
        batch.setShippingCharge(shippingCharge);
        batch.setCareLevel(careLevel);
        batch.setRefrigerationRequired(batchDto.getRefrigerationRequired());

        try {
            batchRepository.save(batch);
            logger.info("Batch saved successfully with batchCode: {}", batchDto.getBatchCode());
        } catch (Exception e) {
            logger.error("Error saving batch: {}", e.getMessage());
            throw new PharmaException("500", "General system error occurred while saving batch");
        }

        // Prepare response
        BatchResponseDto response = new BatchResponseDto();
        response.setBatchCode(batch.getBatchCode());
        response.setMedicineCode(batch.getMedicineCode());
        response.setWeight(batch.getWeight());
        response.setPrice(batch.getPrice());
        response.setMedicineTypeCode(batch.getMedicineTypeCode());
        response.setShippingCharge(batch.getShippingCharge());
        response.setCareLevel(batch.getCareLevel());
        response.setRefrigerationRequired(batch.getRefrigerationRequired());
        response.setMessage("Batch Successfully Added in the System");

        logger.info("Exiting addBatch method with successful response");
        return response;
    }

    private void validateBatch(BatchDto batchDto) {
        logger.debug("Validating batch with batchCode: {}", batchDto.getBatchCode());

        // Check medicine code exists
        if (!medicineMasterRepository.existsByMedicineCode(batchDto.getMedicineCode())) {
            logger.error("Medicine code does not exist: {}", batchDto.getMedicineCode());
            throw new PharmaBusinessException("510", "Medicine Code Does Not Exist");
        }

        // Check batch code doesn't exist
        if (batchRepository.existsByBatchCode(batchDto.getBatchCode())) {
            logger.error("Batch code already exists: {}", batchDto.getBatchCode());
            throw new PharmaBusinessException("511", "Batch Code already exist");
        }

        // Check weight >= 100
        if (batchDto.getWeight() < 100) {
            logger.error("Batch weight is less than 100: {}", batchDto.getWeight());
            throw new PharmaBusinessException("512", "Batch Weight Should be greater than 100");
        }

        // Check batch code format
        if (!batchDto.getBatchCode().matches("BTC-\\d{4}")) {
            logger.error("Invalid batch code format: {}", batchDto.getBatchCode());
            throw new PharmaBusinessException("513", "Batch format wrong. It should be in the format \"BTC-1234\"");
        }
    }

    private BigDecimal calculateShippingCharge(BatchDto batchDto) {
        logger.debug("Calculating shipping charge for batch: {}", batchDto.getBatchCode());

        // Determine weight range
        String weightRange;
        if (batchDto.getWeight() <= 500) {
            weightRange = "W1";
        } else if (batchDto.getWeight() <= 1000) {
            weightRange = "W2";
        } else {
            weightRange = "W3";
        }

        logger.debug("Weight range determined as: {}", weightRange);

        // Get base shipping charge
        Optional<ShippingMaster> shippingMaster = shippingMasterRepository
                .findByMedicineTypeCodeAndWeightRange(batchDto.getMedicineTypeCode(), weightRange);

        if (!shippingMaster.isPresent()) {
            logger.error("Shipping charge not found for medicineTypeCode: {} and weightRange: {}",
                    batchDto.getMedicineTypeCode(), weightRange);
            throw new PharmaBusinessException("514", "Shipping charge configuration not found");
        }

        BigDecimal shippingCharge = shippingMaster.get().getShippingCharge();

        // Apply refrigeration surcharge if needed
        if (batchDto.getRefrigerationRequired()) {
            BigDecimal surcharge = shippingCharge.multiply(new BigDecimal("0.05"));
            shippingCharge = shippingCharge.add(surcharge);
            logger.debug("Applied 5% refrigeration surcharge. New shipping charge: {}", shippingCharge);
        }

        return shippingCharge;
    }

    private String determineCareLevel(String medicineTypeCode) {
        logger.debug("Determining care level for medicineTypeCode: {}", medicineTypeCode);

        switch (medicineTypeCode) {
            case "C":
                return "Normal";
            case "T":
                return "High";
            case "S":
                return "Extremely High";
            default:
                logger.warn("Unknown medicine type code: {}", medicineTypeCode);
                return "Normal";
        }
    }
    @Override
    public BatchResponseDto getBatchById(String batchCode) {
        logger.info("Fetching batch by id: {}", batchCode);

        Optional<Batch> batchOptional = batchRepository.findById(batchCode);
        if (!batchOptional.isPresent()) {
            logger.error("Batch not found with code: {}", batchCode);
            throw new PharmaBusinessException("514", "Batch not found");
        }

        Batch batch = batchOptional.get();
        return convertToResponseDto(batch);
    }

    @Override
    public Page<BatchResponseDto> getAllBatches(Pageable pageable) {
        logger.info("Fetching all batches with pagination - page: {}, size: {}",
                pageable.getPageNumber(), pageable.getPageSize());

        Page<Batch> batchPage = batchRepository.findAll(pageable);

        // Convert Page<Batch> to Page<BatchResponseDto>
        return batchPage.map(this::convertToResponseDto);
    }

    private BatchResponseDto convertToResponseDto(Batch batch) {
        BatchResponseDto response = new BatchResponseDto();
        response.setBatchCode(batch.getBatchCode());
        response.setMedicineCode(batch.getMedicineCode());
        response.setWeight(batch.getWeight());
        response.setPrice(batch.getPrice());
        response.setMedicineTypeCode(batch.getMedicineTypeCode());
        response.setShippingCharge(batch.getShippingCharge());
        response.setCareLevel(batch.getCareLevel());
        response.setRefrigerationRequired(batch.getRefrigerationRequired());
        return response;
    }
}