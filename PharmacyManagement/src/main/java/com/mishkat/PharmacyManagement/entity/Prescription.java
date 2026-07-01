package com.mishkat.PharmacyManagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


@Entity
@Table(name = "prescriptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prescription extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "doctor_name", length = 150)
    private String doctorName;

    @Column(name = "hospital_or_clinic", length = 150)
    private String hospitalOrClinic;

    @Column(name = "prescription_date")
    private LocalDate prescriptionDate;

    @Lob
    @Column(name = "scanned_copy")
    private byte[] scannedCopy;

    // 🟢 নতুন যোগ করা হলো: ফাইলের ধরন (যেমন: image/jpeg বা application/pdf)
    @Column(name = "file_type", length = 100)
    private String fileType;

    @Column(columnDefinition = "TEXT")
    private String remarks;
}
