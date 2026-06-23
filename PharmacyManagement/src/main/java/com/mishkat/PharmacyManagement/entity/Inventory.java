package com.mishkat.PharmacyManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Instantiates the operational item mapping context state
@Data // Automated class pattern structuring via Lombok
@Table(name = "inventories", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"branch_id", "medicine_id"}) // Critical safety rule: Enforces that each unique product gets exactly one summary tracking row per branch. Prevents duplicate rows.
})
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    @Id // Primary key definition
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Primary increment generation mechanics
    private Long id; // Internal surrogate indexing mapping tracker key

    @ManyToOne // Many summarized product records belong inside one specific physical business location (Many-To-One)
    @JoinColumn(name = "branch_id", nullable = false) // Defines mandatory FK column 'branch_id' targeting 'branches' table
    private Branch branch; // The operational branch site holding this inventory tracking row

    @ManyToOne // The exact same product item definition is summarized across many different branch inventories (Many-To-One)
    @JoinColumn(name = "medicine_id", nullable = false) // Defines mandatory FK column 'medicine_id' targeting 'medicines' table
    private Medicine medicine; // The exact catalog medicine item this summary row is calculating

    @Column(nullable = false) // Stock count cannot be initialized to null
    private Integer availableQty = 0; // Total accumulated quick-read calculation of stock (Sum of all unexpired batches at this branch)
}
