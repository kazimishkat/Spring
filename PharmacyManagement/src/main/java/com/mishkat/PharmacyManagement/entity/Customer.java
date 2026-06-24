package com.mishkat.PharmacyManagement.entity;

import com.mishkat.PharmacyManagement.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Customer extends BaseEntity{
    @Column(length = 100)
    private String name;

    private String phone;

    private String email;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;

    private Integer age;

    @Embedded
    private Address address;

    @Column(name = "loyalty_points", nullable = false)
    @Builder.Default
    private Integer loyaltyPoints = 0;
}
