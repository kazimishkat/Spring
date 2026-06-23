package com.mishkat.PharmacyManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Defines database mapping capability
@Data // Generates utility code boilerplate via Lombok
@Table(name = "manufacturers") // Sets table mapping name
@AllArgsConstructor
@NoArgsConstructor
public class Manufacturer {
    @Id // Primary Key mapping
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generation auto-increment framework
    private Long id; // Internal manufacturer entity index identifier

    @Column(nullable = false, unique = true) // Prevents duplication of pharmaceutical drug makers
    private String name; // Formal enterprise group moniker (e.g., "Pfizer", "Square Pharmaceuticals")

    private String country; // Native country of incorporation origin for tracking imported goods
}
