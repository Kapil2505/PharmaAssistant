package com.PharmaAssistant.Entity;




import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "SHIPPING_MASTER")
public class ShippingMaster {

    @Id
    @Column(name = "MEDICINE_TYPE_CODE")
    private String medicineTypeCode;

    @Column(name = "WEIGHT_RANGE")
    private String weightRange;

    @Column(name = "SHIPPING_CHARGE")
    private BigDecimal shippingCharge;

    // Getters and Setters
    public String getMedicineTypeCode() { return medicineTypeCode; }
    public void setMedicineTypeCode(String medicineTypeCode) { this.medicineTypeCode = medicineTypeCode; }
    public String getWeightRange() { return weightRange; }
    public void setWeightRange(String weightRange) { this.weightRange = weightRange; }
    public BigDecimal getShippingCharge() { return shippingCharge; }
    public void setShippingCharge(BigDecimal shippingCharge) { this.shippingCharge = shippingCharge; }
}