package com.mishkat.PharmacyManagement.dto.mapper;

import com.mishkat.PharmacyManagement.dto.requestDTO.PrescriptionRequestDto;
import com.mishkat.PharmacyManagement.dto.responseDTO.PrescriptionResponseDto;
import com.mishkat.PharmacyManagement.entity.Prescription;

public class PrescriptionMapper {
    // =========================================================================
    // 1. Entity থেকে Response DTO তে রূপান্তর (Client-কে ডাটা দেখানোর জন্য)
    // =========================================================================

    public PrescriptionResponseDto toDTO(Prescription entity) {
        if (entity == null) return null;

        PrescriptionResponseDto dto = new PrescriptionResponseDto();

        dto.setId(entity.getId());
        dto.setDoctorName(entity.getDoctorName());
        dto.setHospitalOrClinic(entity.getHospitalOrClinic());
        dto.setPrescriptionDate(entity.getPrescriptionDate());
        dto.setRemarks(entity.getRemarks());

        // [গুরুত্বপূর্ণ নোট]: পারফরম্যান্স ও সিকিউরিটির কথা মাথায় রেখে scannedCopy (byte[])
        // রেসপন্স DTO-তে রাখা হয়নি, তাই ম্যাপ করার প্রয়োজন নেই।
        // সাধারণত ফাইল ডাউনলোডের জন্য আলাদা API Endpoint তৈরি করা হয়।

        // [নাল-চেক]: Lazy Loading-এর ক্ষেত্রে NullPointerException এড়ানোর জন্য Customer চেক করা হচ্ছে
        if (entity.getCustomer() != null) {
            dto.setCustomerId(entity.getCustomer().getId());
            // Customer এন্টিটিতে getName() বা getFullName() আছে ধরে নেওয়া হয়েছে।
            // আপনার এন্টিটির মেথড অনুযায়ী এটি পরিবর্তন করে নিতে পারেন (যেমন: getFirstName())
            dto.setCustomerName(entity.getCustomer().getName());
        }

        return dto;
    }

    // =========================================================================
    // 2. Request DTO থেকে Entity তে রূপান্তর (ডাটাবেজে ডাটা সেভ বা আপডেট করার জন্য)
    // =========================================================================

    public Prescription toEntity(PrescriptionRequestDto dto) {
        if (dto == null) return null;

        Prescription entity = new Prescription();

        entity.setDoctorName(dto.getDoctorName());
        entity.setHospitalOrClinic(dto.getHospitalOrClinic());
        entity.setPrescriptionDate(dto.getPrescriptionDate());
        entity.setScannedCopy(dto.getScannedCopy()); // ফাইল বা ছবির বাইট অ্যারে সরাসরি ম্যাপ করা হচ্ছে
        entity.setRemarks(dto.getRemarks());

        // [বিশেষ দ্রষ্টব্য]: DTO-তে থাকা customerId ব্যবহার করে সরাসরি এন্টিটিতে রিলেশন তৈরি করা যায় না।
        // আপনাকে PrescriptionService ক্লাসে CustomerRepository.findById(dto.getCustomerId()) কল করে
        // আসল Customer অবজেক্টটি বের করে এনে এই entity.setCustomer(...) এর মাধ্যমে সেট করতে হবে।

        return entity;
    }
}
