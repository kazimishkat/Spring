package com.mishkat.PharmacyManagement.dto.responseDTO;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PrescriptionResponseDto {private Long id;
    private Long customerId;
    private String customerName;
    private String doctorName;
    private String hospitalOrClinic;
    private LocalDate prescriptionDate;
    private String remarks;
    // scannedCopy (byte[]) সিকিউরিটি ও পারফরম্যান্সের স্বার্থে সাধারণত
    // এপিআই রেসপন্সে সরাসরি পাঠানো হয় না, আলাদা ডাউনলোড লিঙ্ক দেওয়া হয়।
}
