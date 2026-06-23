package com.mishkat.PharmacyManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Detail tracking sub table mapping representation layer
@Data // Automated structural code patterns by Lombok
@Table(name = "requisition_items") // Binds schema directly to child detail logging table 'requisition_items'
@AllArgsConstructor
@NoArgsConstructor
public class RequisitionItem {
    @Id // Detail record surrogate identifier tracking key index
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automated driver generation handling
    private Long id; // Detail tracking sequence id attribute metric key

    @ManyToOne // Multiple detail product rows map cleanly onto one unique requisition master file sheet (Many-To-One)
    @JoinColumn(name = "requisition_id", nullable = false) // Connects child record firmly down to parent sheet data row identification
    private Requisition requisition; // Bidirectional parent object target anchor context pointer mapping

    @ManyToOne // Many separate store requests across the company can request the exact same catalog item (Many-To-One)
    @JoinColumn(name = "medicine_id", nullable = false) // Foreign key constraint validation tracing down to catalog item
    private Medicine medicine; // The exact catalog medicine configuration being requested by the remote location

    private Integer requestedQty; // Target inventory unit volume count requested by the branch operator
    private Integer fulfilledQty = 0; // Real stock amount volume currently provided and shipped down by the fulfillment center
}
