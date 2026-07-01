package com.mishkat.PharmacyManagement.serviceImp;

import com.mishkat.PharmacyManagement.dto.mapper.PrescriptionMapper;
import com.mishkat.PharmacyManagement.dto.requestDTO.PrescriptionRequestDto;
import com.mishkat.PharmacyManagement.dto.responseDTO.PrescriptionResponseDto;
import com.mishkat.PharmacyManagement.entity.Customer;
import com.mishkat.PharmacyManagement.entity.Prescription;
import com.mishkat.PharmacyManagement.repository.CustomerRepository;
import com.mishkat.PharmacyManagement.repository.PrescriptionRepository;
import com.mishkat.PharmacyManagement.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrescriptionServiceImpl implements PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final CustomerRepository customerRepository;

    private final PrescriptionMapper mapper = new PrescriptionMapper();

    @Override
    @Transactional
    public PrescriptionResponseDto createPrescription(PrescriptionRequestDto dto, MultipartFile file) {
        // ১. সাধারণ ডেটাগুলো DTO থেকে Entity-তে ম্যাপ করা
        Prescription prescription = mapper.toEntity(dto);

        // ২. ফাইল থাকলে সরাসরি Entity-তে byte[] এবং fileType সেট করা
        if (file != null && !file.isEmpty()) {
            try {
                prescription.setScannedCopy(file.getBytes());
                prescription.setFileType(file.getContentType());
            } catch (IOException e) {
                throw new RuntimeException("Failed to process scanned copy file", e);
            }
        }

        // ৩. Customer অবজেক্ট সেট করা
        if (dto.getCustomerId() != null) {
            Customer customer = customerRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found with id: " + dto.getCustomerId()));
            prescription.setCustomer(customer);
        }

        Prescription savedPrescription = prescriptionRepository.save(prescription);
        return mapper.toDTO(savedPrescription);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PrescriptionResponseDto> getAllPrescriptions() {
        return prescriptionRepository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PrescriptionResponseDto getPrescriptionById(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found with id: " + id));
        return mapper.toDTO(prescription);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PrescriptionResponseDto> getPrescriptionsByCustomerId(Long customerId) {
        return prescriptionRepository.findByCustomerId(customerId).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PrescriptionResponseDto> searchByDoctorName(String doctorName) {
        return prescriptionRepository.findByDoctorNameContainingIgnoreCase(doctorName).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PrescriptionResponseDto updatePrescription(Long id, PrescriptionRequestDto dto, MultipartFile file) {
        Prescription existingPrescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found with id: " + id));

        // সাধারণ ডেটা আপডেট
        if (dto.getDoctorName() != null) existingPrescription.setDoctorName(dto.getDoctorName());
        if (dto.getHospitalOrClinic() != null) existingPrescription.setHospitalOrClinic(dto.getHospitalOrClinic());
        if (dto.getPrescriptionDate() != null) existingPrescription.setPrescriptionDate(dto.getPrescriptionDate());
        if (dto.getRemarks() != null) existingPrescription.setRemarks(dto.getRemarks());

        // নতুন ফাইল আসলে আপডেট করা
        if (file != null && !file.isEmpty()) {
            try {
                existingPrescription.setScannedCopy(file.getBytes());
                existingPrescription.setFileType(file.getContentType());
            } catch (IOException e) {
                throw new RuntimeException("Failed to process scanned copy file", e);
            }
        }

        // কাস্টমার পরিবর্তন হলে আপডেট করা
        if (dto.getCustomerId() != null &&
                (existingPrescription.getCustomer() == null || !existingPrescription.getCustomer().getId().equals(dto.getCustomerId()))) {
            Customer customer = customerRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found with id: " + dto.getCustomerId()));
            existingPrescription.setCustomer(customer);
        }

        Prescription savedPrescription = prescriptionRepository.save(existingPrescription);
        return mapper.toDTO(savedPrescription);
    }

    @Override
    @Transactional
    public void deletePrescription(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found with id: " + id));
        prescriptionRepository.delete(prescription);
    }

    @Override
    @Transactional(readOnly = true)
    public Prescription getPrescriptionEntityById(Long id) {
        return prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found with id: " + id));
    }
}
