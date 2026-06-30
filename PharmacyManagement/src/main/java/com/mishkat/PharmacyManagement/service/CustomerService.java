package com.mishkat.PharmacyManagement.service;

import com.mishkat.PharmacyManagement.dto.requestDTO.CustomerRequestDto;
import com.mishkat.PharmacyManagement.dto.responseDTO.CustomerResponseDto;

import java.util.List;

public interface CustomerService {
    CustomerResponseDto createCustomer(CustomerRequestDto dto);

    List<CustomerResponseDto> getAllCustomers();

    List<CustomerResponseDto> getActiveCustomers();

    CustomerResponseDto getCustomerById(Long id);

    CustomerResponseDto getCustomerByPhone(String phone);

    CustomerResponseDto getCustomerByEmail(String email);

    CustomerResponseDto updateCustomer(Long id, CustomerRequestDto dto);

    void deleteCustomer(Long id);
}
