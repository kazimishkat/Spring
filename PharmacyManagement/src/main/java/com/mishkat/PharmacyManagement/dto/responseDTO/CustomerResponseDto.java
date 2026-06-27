package com.mishkat.PharmacyManagement.dto.responseDTO;

import com.mishkat.PharmacyManagement.enums.Gender;
import lombok.Data;

@Data
public class CustomerResponseDto {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private Gender gender;
    private Integer age;
    private AddressResponseDto address;
    private Integer loyaltyPoints;
    private Boolean isActive;
}
