package com.mishkat.PharmacyManagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity // Defines entity serialization layer
@Data // Injects standard Lombok data constructors and structural patterns
@Table(name = "batch_stocks") // Binds entity model to 'batch_stocks' table
public class BatchStock {
    @Id // Identifies database reference record row primary ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatic ID numbering incrementation tracking
    private Long id; // Internal processing row primary key tracking item

    @ManyToOne // Many discrete batches can point back to the same base cataloged item definition (Many-To-One)
    @JoinColumn(name = "medicine_id", nullable = false) // Explicitly declares mandatory foreign key column mapping 'medicine_id'
    private Medicine medicine; // The base structural master catalog item definition this batch belongs to

    @ManyToOne // A single location branch holds many specific unique batch items on shelves (Many-To-One)
    @JoinColumn(name = "branch_id", nullable = false) // Maps mandatory physical localization reference key 'branch_id'
    private Branch branch; // The designated physical branch store where this specific batch sits

    @Column(nullable = false) // Batch identifier tags are strictly mandatory for pharmaceutical tracking
    private String batchNo; // Manufacturer assigned unique production batch tracking identifier (e.g., "B-NAPA-2026-09")

    @Temporal(TemporalType.DATE) // Forces truncation of time metrics, saving only raw date information (YYYY-MM-DD)
    private Date manufactureDate; // The specific calendar date of factory creation for shelf life lifecycle metrics

    @Temporal(TemporalType.DATE) // Instructs JPA to store calendar date criteria specifically in database engine
    private Date expiryDate; // Critical threshold date marking when the batch spoils and must be locked from retail sale

    @Column(nullable = false) // Quantity must be zero or above, never unspecified or null
    private Integer quantity; // The current absolute real-time count of physical items remaining for this unique batch at this branch

    private Double purchasePrice; // Actual batch specific cost value (accounting for price fluctuations over time)
    private Double salePrice; // Actual batch targeted checkout price (accounting for market changes)
}
