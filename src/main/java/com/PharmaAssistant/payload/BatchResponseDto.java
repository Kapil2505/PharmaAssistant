package com.PharmaAssistant.payload;

import java.math.BigDecimal;

public class BatchResponseDto {
    private String batchCode;
    private String medicineCode;
    private Integer weight;
    private BigDecimal price;
    private String medicineTypeCode;
    private BigDecimal shippingCharge;
    private String careLevel;
    private Boolean refrigerationRequired;
    private String message;

    public BatchResponseDto() {
        // Default constructor
    }
    // Getters and Setters
    public String getBatchCode() { return batchCode; }
    public void setBatchCode(String batchCode) { this.batchCode = batchCode; }
    public String getMedicineCode() { return medicineCode; }
    public void setMedicineCode(String medicineCode) { this.medicineCode = medicineCode; }
    public Integer getWeight() { return weight; }
    public void setWeight(Integer weight) { this.weight = weight; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public String getMedicineTypeCode() { return medicineTypeCode; }
    public void setMedicineTypeCode(String medicineTypeCode) { this.medicineTypeCode = medicineTypeCode; }
    public BigDecimal getShippingCharge() { return shippingCharge; }
    public void setShippingCharge(BigDecimal shippingCharge) { this.shippingCharge = shippingCharge; }
    public String getCareLevel() { return careLevel; }
    public void setCareLevel(String careLevel) { this.careLevel = careLevel; }
    public Boolean getRefrigerationRequired() { return refrigerationRequired; }
    public void setRefrigerationRequired(Boolean refrigerationRequired) { this.refrigerationRequired = refrigerationRequired; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
