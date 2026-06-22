package com.mishkat.PharmacyManagement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity // Marks this Java class as a JPA database entity mapped to a table
@Data // Lombok annotation generating getters, setters, toString, equals, and hashCode
@Table(name = "branches") // Maps entity to the explicit database table named 'branches'
public class Branch {
    @Id // Declares this field as the Primary Key of the table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically auto-increments the ID value (MySQL/PostgreSQL native auto-increment)
    private Long id; // Unique internal identifier for the branch

    @Column(unique = true, nullable = false) // Configures database constraint: Cannot be null, must be 100% unique
    private String code; // Unique short code representing the branch (e.g., "BR-DHK-01")

    @Column(nullable = false) // Ensures name cannot be omitted or null in the database
    private String name; // Legal descriptive name of the pharmacy branch

    private String phone; // Contact phone line for the branch
    private String email; // Official branch correspondence email address
    private String address; // Full physical street address of the branch

    private boolean active = true; // Flag for soft-deleting/deactivating branches without breaking historical relations
}
