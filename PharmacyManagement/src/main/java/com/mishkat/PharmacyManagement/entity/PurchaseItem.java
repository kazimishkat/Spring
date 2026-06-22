package com.mishkat.PharmacyManagement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity // Set detail line item definition attributes mapping
@Data // Injects standard element access mechanisms via Lombok
@Table(name = "purchase_items") // Binds entity schema inside 'purchase_items' table data framework
public class PurchaseItem {
    @Id // Individual details primary tracking key assignment
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automated identity sequence driver processing
    private Long id; // Individual line tracking internal database index marker key

    @ManyToOne // Many detail rows group cleanly under a single parent transaction document record header (Many-To-One)
    @JoinColumn(name = "purchase_id", nullable = false) // Declares foreign key link pointing to parent document header
    private Purchase purchase; // Explicit structural pointer mapping cleanly back up to parent purchase order

    @ManyToOne // Many separate order line rows across different purchase files can request the same product (Many-To-One)
    @JoinColumn(name = "medicine_id", nullable = false) // Maps structural component linking to specific item master inventory criteria
    private Medicine medicine; // The explicit product inventory identity requested for procurement delivery

    private String batchNo; // Expected or assigned production tracking lot code recorded at receiving terminal dock doors
    private Integer quantityOrdered; // Intended target supply volume item units originally requested from supplier
    private Integer quantityReceived; // Real inventory count actually processed, verified, and placed on shelves upon receipt
    private Double unitPurchasePrice; // Agreed contractual cost price paid per unit to buy this batch item from supplier
}
