package com.mishkat.PharmacyManagement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity // Marks this as a JPA persistence table entity
@Data // Automates encapsulation patterns via Lombok
@Table(name = "suppliers") // Configures database destination table
public class Supplier {

    @Id // Primary Key marker
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Identity strategy configuration
    private Long id; // Unique surrogate ID for the supplier

    @Column(unique = true, nullable = false) // Validates distinct identity code
    private String supplierCode; // Unique vendor ID identifier (e.g., "SUP-ACME-09")

    @Column(nullable = false) // Compulsory profile field
    private String supplierName; // Official business title of the vendor

    private String contactPerson; // Primary individual handler representing the vendor company
    private String mobile; // Phone number for procurement communications
    private String email; // Enterprise business email for dispatching purchase orders
    private String address; // Logistics facility distribution headquarters address
}
