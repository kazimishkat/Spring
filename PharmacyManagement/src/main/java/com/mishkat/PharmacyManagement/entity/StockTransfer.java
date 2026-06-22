package com.mishkat.PharmacyManagement.entity;

import com.mishkat.PharmacyManagement.enums.TransferStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity // Sets database framework for managing internal point-to-point shipments
@Data // Lombok tool automation application settings
@Table(name = "stock_transfers") // Binds parent configurations directly inside 'stock_transfers' table
public class StockTransfer {
    @Id // Document transaction locator key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autonumber indexing strategy specification
    private Long id; // Internal sequence reference row index number

    @Column(unique = true, nullable = false) // Manifest reference codes must be entirely unique for asset protection mapping
    private String transferNo; // Shipping manifest serial reference track string identifier (e.g., "TR-BR01-BR05-4412")

    @ManyToOne // A branch location acts as the shipment origin for many independent stock transfers (Many-To-One)
    @JoinColumn(name = "from_branch_id", nullable = false) // Creates foreign key tracking mapping tracing back to source origin branch
    private Branch fromBranch; // The source shipping branch location transferring inventory away from its local shelves

    @ManyToOne // A branch location can act as the target recipient for many independent stock transfers (Many-To-One)
    @JoinColumn(name = "to_branch_id", nullable = false) // Creates foreign key tracking mapping tracing to receiving branch
    private Branch toBranch; // The target destination location receiving incoming transferred inventory onto its local shelves

    @Temporal(TemporalType.TIMESTAMP) // Tracks accurate logistics scheduling metadata timeline metrics
    private Date transferDate = new Date(); // The exact execution moment when this transit manifest document initialized

    @Enumerated(EnumType.STRING) // Saves state values clearly inside database rows as string values
    private TransferStatus status; // Current logistical routing pipeline state tracking this shipment's progress

    @OneToMany(mappedBy = "stockTransfer", cascade = CascadeType.ALL, orphanRemoval = true) // One-To-Many structural alignment mapping individual product lines on this shipment. Cascade operations manage child record lifecycles directly.
    private List<StockTransferItem> items = new ArrayList<>(); // Collective breakdown list detailing all physical inventory components inside this transit container
}
