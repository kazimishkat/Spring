package com.mishkat.PharmacyManagement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity // Establishes entity object context
@Data // Sets standard access rules methods
@Table(name = "generics") // Links object to 'generics' DB table
public class Generic {
    @Id // Identity attribute configuration
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Database native indexing strategy
    private Long id; // Generic formulation mapping identifier

    @Column(nullable = false, unique = true) // Disallows duplicate generic formulation records
    private String genericName; // International Nonproprietary Name formulation (e.g., "Paracetamol", "Omeprazole")

    @Column(columnDefinition = "TEXT") // Tells Hibernate to use a large text object type (CLOB/TEXT) instead of default VARCHAR(255)
    private String description; // Summary breakdown details of the chemical compound functionality

    @Column(columnDefinition = "TEXT") // Allocates unstructured deep text storage space in DB
    private String indication; // Detailed description of medical conditions this formula is used to treat

    private String dosage; // General consumption instruction paradigms (e.g., "500mg daily", "With meals")

    @Column(columnDefinition = "TEXT") // Large text field for comprehensive list of clinical side effects
    private String sideEffects; // Documented potential negative reactions and adverse responses

    @Column(columnDefinition = "TEXT") // Custom large text allocation for critical medical safeguards
    private String precautions; // Warning triggers, high-risk groups, or drug conflict conditions
}
