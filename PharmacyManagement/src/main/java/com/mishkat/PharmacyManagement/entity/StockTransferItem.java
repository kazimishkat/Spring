package com.mishkat.PharmacyManagement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity // Child table detailing content items inside transit manifests
@Data // Auto-builds necessary access mechanisms via Lombok attributes
@Table(name = "stock_transfer_items") // Houses asset entries inside 'stock_transfer_items' data container
public class StockTransferItem {
    @Id // Sub record sequence tracker identification index key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Setup generation execution sequence context
    private Long id; // Secondary surrogate locator code reference tracking row key

    @ManyToOne // Many cargo manifest lines belong together under one unique master transit manifest tracking sheet (Many-To-One)
    @JoinColumn(name = "stock_transfer_id", nullable = false) // Links explicitly down to parent document record identity number
    private StockTransfer stockTransfer; // Direct relationship link back up to parent shipping document structure mapping

    @ManyToOne // Multiple distinct branch transfers can move the same catalog item around the company network (Many-To-One)
    @JoinColumn(name = "medicine_id", nullable = false) // Mandatory reference identifier linking catalog asset details to this row
    private Medicine medicine; // The explicit medicine item classification being shipped between branch stores

    private String batchNo; // Exact manufacturer batch lot code assignment indicating which specific physical cases are in transit
    private Integer quantity; // Exact volume item count representing the total inventory transferred on this manifest line
}
