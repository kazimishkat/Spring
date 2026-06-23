package com.mishkat.PharmacyManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Declares table definition model
@Data // Lombok variable configuration automation
@Table(name = "customers") // Ties model to 'customers' DB structure
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id // Primary ID indicator
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures field generation pattern
    private Long id; // Unique indexing registry for the consumer

    @Column(nullable = false) // Customer name cannot be missing
    private String customerName; // Regular title or name of the patron patient

    @Column(unique = true) // Guarantees phone numbers can uniquely load returning customer accounts
    private String mobile; // Contact mobile link for tracking patient record accounts

    private String address; // Residence details for deliveries or localized tracking
}
