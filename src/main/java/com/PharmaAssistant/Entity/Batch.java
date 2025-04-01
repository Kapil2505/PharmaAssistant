package com.PharmaAssistant.Entity;




import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "BATCH_INFO")
public class Batch {

    @Id
    @Column(name = "BATCH_CODE")
    private String batchCode;

    @Column(name = "MEDICINE_CODE")
    private String medicineCode;

    @Column(name = "WEIGHT")
    private Integer weight;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "MEDICINE_TYPE_CODE")
    private String medicineTypeCode;

    @Column(name = "SHIPPING_CHARGE")
    private BigDecimal shippingCharge;

    @Column(name = "CARE_LEVEL")
    private String careLevel;

    @Column(name = "REFRIGERATION_REQUIRED")
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
    public BigDecimal getShippingCharge() { return shippingCharge; }
    public void setShippingCharge(BigDecimal shippingCharge) { this.shippingCharge = shippingCharge; }
    public String getCareLevel() { return careLevel; }
    public void setCareLevel(String careLevel) { this.careLevel = careLevel; }
    public Boolean getRefrigerationRequired() { return refrigerationRequired; }
    public void setRefrigerationRequired(Boolean refrigerationRequired) { this.refrigerationRequired = refrigerationRequired; }
}