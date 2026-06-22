package com.mishkat.PharmacyManagement.entity;

import com.mishkat.PharmacyManagement.enums.PurchaseStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity // Setup mapping for external procurement order tracker
@Data // Automated structural code generator deployment via Lombok
@Table(name = "purchases") // Assigns parent header info block to 'purchases' table database location
public class Purchase {
    @Id // Standard identifier attribute tag setting
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Primary row sequence auto tracking configuration
    private Long id; // Primary document transactional reference index identifier

    @Column(unique = true, nullable = false) // Enforces rigid compliance tracking identifier boundaries
    private String poNumber; // Unique corporate Purchase Order invoice number string identifier (e.g., "PO-2026-8772")

    @ManyToOne // A single supply vendor can receive multiple purchase orders from our company over time (Many-To-One)
    @JoinColumn(name = "supplier_id", nullable = false) // Specifies mandatory Foreign Key targeting the 'suppliers' database structure
    private Supplier supplier; // Vendor party business targeted to receive this specific supply inventory order request

    @ManyToOne // Many distinct corporate purchase orders can be ordered to ship into the same receiving branch hub (Many-To-One)
    @JoinColumn(name = "branch_id", nullable = false) // Directs incoming warehouse mapping context via mandatory Foreign Key column reference link
    private Branch branch; // Destination branch location warehouse receiving and processing these incoming cargo goods

    @Temporal(TemporalType.TIMESTAMP) // Tracks calendar log metadata timing values accurately
    private Date purchaseDate; // Log date time tracking when the procurement order event record was initialized

    private Double totalAmount; // Total financial investment expense liability tracking calculation sum for all child items ordered

    @Enumerated(EnumType.STRING) // Saves order status explicitly as clear database text string attributes
    private PurchaseStatus status; // Operations procurement status tracking the current phase of this supply order

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true) // One-To-Many composition linkage tracking multiple row entries on this order sheet. Cascade operations manage child record lifecycles directly.
    private List<PurchaseItem> items = new ArrayList<>(); // Aggregated list collection mapping every item requested on this order sheet
}
