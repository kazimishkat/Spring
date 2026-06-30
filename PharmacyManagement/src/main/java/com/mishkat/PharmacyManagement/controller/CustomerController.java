package com.mishkat.PharmacyManagement.controller;

import com.mishkat.PharmacyManagement.dto.requestDTO.CustomerRequestDto;
import com.mishkat.PharmacyManagement.dto.responseDTO.CustomerResponseDto;
import com.mishkat.PharmacyManagement.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    // ── Customer Management ──────────────────────────────────────────

    // POST /api/customers
    @PostMapping
    public ResponseEntity<CustomerResponseDto> create(
            @Valid @RequestBody CustomerRequestDto dto) {
        return new ResponseEntity<>(customerService.createCustomer(dto), HttpStatus.CREATED);
    }

    // GET /api/customers
    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> getAll() {
        List<CustomerResponseDto> list = customerService.getAllCustomers();
        return list.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(list);
    }

    // GET /api/customers/active
    @GetMapping("/active")
    public ResponseEntity<List<CustomerResponseDto>> getActiveCustomers() {
        List<CustomerResponseDto> list = customerService.getActiveCustomers();
        return list.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(list);
    }

    // GET /api/customers/1
    @GetMapping("/{id}")
    public CustomerResponseDto getById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    // GET /api/customers/phone/017XXXXXXXX
    @GetMapping("/phone/{phone}")
    public CustomerResponseDto getByPhone(@PathVariable String phone) {
        return customerService.getCustomerByPhone(phone);
    }

    // GET /api/customers/email/test@test.com
    @GetMapping("/email/{email}")
    public CustomerResponseDto getByEmail(@PathVariable String email) {
        return customerService.getCustomerByEmail(email);
    }

    // PUT /api/customers/1
    @PutMapping("/{id}")
    public CustomerResponseDto update(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequestDto dto) {
        return customerService.updateCustomer(id, dto);
    }

    // DELETE /api/customers/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Customer deleted successfully");
    }
}
