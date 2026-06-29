package com.mishkat.PharmacyManagement.dto.mapper;

import com.mishkat.PharmacyManagement.dto.requestDTO.CustomerRequestDto;
import com.mishkat.PharmacyManagement.dto.responseDTO.AddressResponseDto;
import com.mishkat.PharmacyManagement.dto.responseDTO.CustomerResponseDto;
import com.mishkat.PharmacyManagement.entity.Address;
import com.mishkat.PharmacyManagement.entity.Customer;

public class CustomerMapper {
    // Entity → Response DTO
    public static CustomerResponseDto toDTO(Customer customer) {
        if (customer == null) return null;

        CustomerResponseDto dto = new CustomerResponseDto();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setPhone(customer.getPhone());
        dto.setEmail(customer.getEmail());
        dto.setGender(customer.getGender());
        dto.setAge(customer.getAge());
        dto.setLoyaltyPoints(customer.getLoyaltyPoints());
        dto.setIsActive(customer.getIsActive());

        // Embedded Address কনভার্শন
        if (customer.getAddress() != null) {
            AddressResponseDto addressDto = new AddressResponseDto();
            addressDto.setAddressLine1(customer.getAddress().getAddressLine1());
            addressDto.setAddressLine2(customer.getAddress().getAddressLine2());
            addressDto.setCity(customer.getAddress().getCity());
            addressDto.setState(customer.getAddress().getState());
            addressDto.setPostalCode(customer.getAddress().getPostalCode());
            addressDto.setCountry(customer.getAddress().getCountry());
            dto.setAddress(addressDto);
        }

        return dto;
    }

    // Request DTO → Entity
    public static Customer toEntity(CustomerRequestDto dto) {
        if (dto == null) return null;

        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setPhone(dto.getPhone());
        customer.setEmail(dto.getEmail());
        customer.setGender(dto.getGender());
        customer.setAge(dto.getAge());

        // নতুন কাস্টমার রেজিস্ট্রেশনের সময় তার পয়েন্ট ডিফল্টভাবে ০ থাকবে
        customer.setLoyaltyPoints(0);

        // Embedded Address সেট করা
        if (dto.getAddress() != null) {
            Address address = new Address();
            address.setAddressLine1(dto.getAddress().getAddressLine1());
            address.setAddressLine2(dto.getAddress().getAddressLine2());
            address.setCity(dto.getAddress().getCity());
            address.setState(dto.getAddress().getState());
            address.setPostalCode(dto.getAddress().getPostalCode());
            address.setCountry(dto.getAddress().getCountry());
            customer.setAddress(address);
        }

        return customer;
    }

    // Apply request DTO onto existing entity (for update)
    public static void updateEntity(Customer customer, CustomerRequestDto dto) {
        if (dto == null) return;

        if (dto.getName() != null) customer.setName(dto.getName());
        if (dto.getPhone() != null) customer.setPhone(dto.getPhone());
        if (dto.getEmail() != null) customer.setEmail(dto.getEmail());
        if (dto.getGender() != null) customer.setGender(dto.getGender());
        if (dto.getAge() != null) customer.setAge(dto.getAge());

        // Address আপডেট লজিক (নিরাপদ Null-safety সহ)
        if (dto.getAddress() != null) {
            Address address = customer.getAddress();
            if (address == null) {
                address = new Address();
                customer.setAddress(address);
            }
            if (dto.getAddress().getAddressLine1() != null) address.setAddressLine1(dto.getAddress().getAddressLine1());
            if (dto.getAddress().getAddressLine2() != null) address.setAddressLine2(dto.getAddress().getAddressLine2());
            if (dto.getAddress().getCity() != null) address.setCity(dto.getAddress().getCity());
            if (dto.getAddress().getState() != null) address.setState(dto.getAddress().getState());
            if (dto.getAddress().getPostalCode() != null) address.setPostalCode(dto.getAddress().getPostalCode());
            if (dto.getAddress().getCountry() != null) address.setCountry(dto.getAddress().getCountry());
        }
    }
}
