package com.PharmaAssistant.Entity;


import javax.persistence.*;

@Entity
@Table(name = "MEDICINE_MASTER")
public class MedicineMaster {

    @Id
    @Column(name = "MEDICINE_CODE")
    private String medicineCode;

    @Column(name = "MEDICINE_NAME")
    private String medicineName;

    // Getters and Setters
    public String getMedicineCode() { return medicineCode; }
    public void setMedicineCode(String medicineCode) { this.medicineCode = medicineCode; }
    public String getMedicineName() { return medicineName; }
    public void setMedicineName(String medicineName) { this.medicineName = medicineName; }
}