package com.PharmaAssistant.payload;


import java.math.BigDecimal;

public class BatchDto {
    private String batchCode;
    private String medicineCode;
    private Integer weight;
    private BigDecimal price;
    private String medicineTypeCode;
    private Boolean refrigerationRequired;

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
    public Boolean getRefrigerationRequired() { return refrigerationRequired; }
    public void setRefrigerationRequired(Boolean refrigerationRequired) { this.refrigerationRequired = refrigerationRequired; }
}