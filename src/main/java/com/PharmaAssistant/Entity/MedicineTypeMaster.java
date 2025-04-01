package com.PharmaAssistant.Entity;


import javax.persistence.*;

@Entity
@Table(name = "MEDICINE_TYPE_MASTER")
public class MedicineTypeMaster {

    @Id
    @Column(name = "MEDICINE_TYPE_CODE")
    private String medicineTypeCode;

    @Column(name = "MEDICINE_TYPE_NAME")
    private String medicineTypeName;

    // Getters and Setters
    public String getMedicineTypeCode() { return medicineTypeCode; }
    public void setMedicineTypeCode(String medicineTypeCode) { this.medicineTypeCode = medicineTypeCode; }
    public String getMedicineTypeName() { return medicineTypeName; }
    public void setMedicineTypeName(String medicineTypeName) { this.medicineTypeName = medicineTypeName; }
}