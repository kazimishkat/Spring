package com.mishkat.PharmacyManagement.entity;

import com.mishkat.PharmacyManagement.enums.StockMovementType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity // Establishes a read-only immutable core audit log ledger tracking all inventory changes
@Data // Standard Lombok utility integration tool configuration
@Table(name = "stock_movements") // Maps schema definition into central inventory ledger table 'stock_movements'
public class StockMovement {
    @Id // Audit event transaction locator unique identification id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Incremental security numbering framework execution sequence
    private Long id; // Unique event record sequence identifier tracking index key

    @ManyToOne // A single physical branch maps thousands of individual inventory event changes onto this ledger (Many-To-One)
    @JoinColumn(name = "branch_id", nullable = false) // Foreign key pinpointing exactly where this inventory event took place
    private Branch branch; // The operational branch site where this physical stock balance change occurred

    @ManyToOne // The exact same catalog medicine is referenced across thousands of movement events across the company (Many-To-One)
    @JoinColumn(name = "medicine_id", nullable = false) // Foreign key identifying exactly which item's stock level changed
    private Medicine medicine; // The target catalog item asset affected by this physical stock movement event

    private String batchNo; // The exact production batch lot code adjusted during this movement event (crucial for tracking expirations)

    @Enumerated(EnumType.STRING) // Saves transaction types clearly as text values inside the database engine
    @Column(nullable = false) // Transaction classification must be strictly defined for reporting compliance
    private StockMovementType movementType; // Audit log classification tag defining the reason behind this stock level change

    @Column(nullable = false) // Balance change volume values cannot be omitted or set to null
    private Integer quantity; // The signed variance delta quantity count (+50 for incoming purchases, -10 for outgoing sales)

    @Temporal(TemporalType.TIMESTAMP) // Capture absolute date and precision execution clock timing
    private Date movementDate = new Date(); // The exact real-time moment when this inventory balance change hit the system

    private String referenceNo; // Cross-reference invoice number or document id linking back to the source transaction (e.g., matching InvoiceNo, PONumber, TransferNo)
}
