package com.mishkat.PharmacyManagement.entity;

import com.mishkat.PharmacyManagement.enums.RequisitionStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity // Instantiates the structural internal branch request framework table mapping
@Data // Automates Java field encapsulation mechanisms via Lombok
@Table(name = "requisitions") // Directs configuration to parent table 'requisitions'
public class Requisition {

    @Id // Internal row locator assignment
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Driver configuration indexing engine
    private Long id; // Internal processing row tracking target record index code

    @Column(unique = true, nullable = false) // Requisition request files must be uniquely tracked to prevent system noise
    private String requisitionNo; // Unique tracking ticket number identifier tracking this stock request (e.g., "REQ-BR02-9912")

    @ManyToOne // One single branch location can file many independent stock replenishment tickets over time (Many-To-One)
    @JoinColumn(name = "branch_id", nullable = false) // Connects the requesting branch directly through foreign key constraints setup mapping
    private Branch branch; // The specific localized store requesting urgent stock replenishment from central warehouse

    @Temporal(TemporalType.TIMESTAMP) // Captures accurate administrative file log timestamps
    private Date requisitionDate = new Date(); // The exact administrative submission date and time tracking when this ticket opened

    @Enumerated(EnumType.STRING) // Saves execution condition properties as text values
    private RequisitionStatus status; // Core lifecycle validation tracker state monitoring the progress of internal branch fulfillment

    @OneToMany(mappedBy = "requisition", cascade = CascadeType.ALL, orphanRemoval = true) // One-To-Many relationship defining all line items grouped on this requisition sheet. Cascade operations manage child record lifecycles directly.
    private List<RequisitionItem> items = new ArrayList<>(); // Comprehensive list tracking all distinct catalog assets needed by this branch
}
