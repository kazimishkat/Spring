package com.mishkat.PharmacyManagement.controller;

import com.mishkat.PharmacyManagement.dto.requestDTO.PrescriptionRequestDto;
import com.mishkat.PharmacyManagement.dto.responseDTO.PrescriptionResponseDto;
import com.mishkat.PharmacyManagement.entity.Prescription;
import com.mishkat.PharmacyManagement.service.PrescriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

    // POST /api/prescriptions
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PrescriptionResponseDto> create(
            @RequestPart("prescription") @Valid PrescriptionRequestDto dto,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        return new ResponseEntity<>(prescriptionService.createPrescription(dto, file), HttpStatus.CREATED);
    }

    // GET /api/prescriptions
    @GetMapping
    public ResponseEntity<List<PrescriptionResponseDto>> getAll() {
        List<PrescriptionResponseDto> list = prescriptionService.getAllPrescriptions();
        return list.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(list);
    }

    // GET /api/prescriptions/1
    @GetMapping("/{id}")
    public PrescriptionResponseDto getById(@PathVariable Long id) {
        return prescriptionService.getPrescriptionById(id);
    }

    // GET /api/prescriptions/customer/5
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<PrescriptionResponseDto>> getByCustomerId(@PathVariable Long customerId) {
        List<PrescriptionResponseDto> list = prescriptionService.getPrescriptionsByCustomerId(customerId);
        return list.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(list);
    }

    // GET /api/prescriptions/search?doctorName=Smith
    @GetMapping("/search")
    public ResponseEntity<List<PrescriptionResponseDto>> searchByDoctorName(
            @RequestParam String doctorName) {
        List<PrescriptionResponseDto> list = prescriptionService.searchByDoctorName(doctorName);
        return list.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(list);
    }

    // PUT /api/prescriptions/1
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PrescriptionResponseDto update(
            @PathVariable Long id,
            @RequestPart("prescription") @Valid PrescriptionRequestDto dto,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        return prescriptionService.updatePrescription(id, dto, file);
    }

    // DELETE /api/prescriptions/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        prescriptionService.deletePrescription(id);
        return ResponseEntity.ok("Prescription deleted successfully");
    }

    // =================================================================
    // ⬇️ Dynamic File Download Endpoint
    // =================================================================

    // GET /api/prescriptions/1/download
    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadScannedCopy(@PathVariable Long id) {

        Prescription prescription = prescriptionService.getPrescriptionEntityById(id);

        if (prescription.getScannedCopy() == null) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();

        // ডাইনামিকালি ফাইলের ধরন সেট করা হচ্ছে (PDF / Image)
        if (prescription.getFileType() != null) {
            headers.setContentType(MediaType.parseMediaType(prescription.getFileType()));
        } else {
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); // Fallback
        }

        // ফাইলের সঠিক এক্সটেনশন যোগ করা
        String ext = (prescription.getFileType() != null && prescription.getFileType().contains("pdf")) ? ".pdf" : ".jpg";
        headers.setContentDispositionFormData("attachment", "prescription_" + id + ext);

        return new ResponseEntity<>(prescription.getScannedCopy(), headers, HttpStatus.OK);
    }
}
