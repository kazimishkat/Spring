package com.mishkat.PharmacyManagement.entity;

import com.mishkat.PharmacyManagement.enums.PaymentMethod;
import com.mishkat.PharmacyManagement.enums.SaleStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity // Declares main sales transaction engine mapping
@Data // Applies standard structural patterns via Lombok encapsulation
@Table(name = "sales") // Stores parent headers in 'sales' table
public class Sale {
    @Id // Master Primary ID field allocation
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Key increment strategy specification
    private Long id; // Internal tracking sales identifier index code

    @Column(unique = true, nullable = false) // Invoice codes must be absolutely unique for legal auditing
    private String invoiceNo; // External system invoice tracking number string identifier (e.g., "INV-2026-00001")

    @ManyToOne // Many independent retail sales transactions originate out from a single specific location branch (Many-To-One)
    @JoinColumn(name = "branch_id", nullable = false) // Declares mandatory foreign key 'branch_id' to trace which store made the revenue
    private Branch branch; // The source branch location that processed this sale transaction

    @ManyToOne // A customer can place many separate invoices over their lifecycle (Many-To-One)
    @JoinColumn(name = "customer_id") // Creates an optional FK 'customer_id' to trace the shopper. Nullable for walk-in anonymous sales.
    private Customer customer; // Patient account user linked to this transaction history tracker

    @Temporal(TemporalType.TIMESTAMP) // Captures exact accurate date AND calendar clock time for precise audit logs
    private Date saleDate = new Date(); // Accurate chronological moment of processing checkout fulfillment

    private Double totalAmount; // Sum raw retail cost value calculation of all items purchased before discounts
    private Double discountAmount = 0.0; // Price deduction allowance applied to this overall transaction instance
    private Double payableAmount; // Final financial liability total charged to customer after processing deductions (`totalAmount - discountAmount`)

    @Enumerated(EnumType.STRING) // Maps enum state directly to database column as text
    private SaleStatus status; // Settlement status flag monitoring context state values of checkout lifecycle

    @Enumerated(EnumType.STRING) // Saves enum choice string directly into database record row field
    private PaymentMethod paymentMethod; // Direct routing methodology mechanism chosen to clear processing fees

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true) // One parent Sale header contains multiple child line items (One-To-Many). `mappedBy = "sale"` establishes bidirectional mapping pointing to the field inside the child object. `CascadeType.ALL` means if you save, update, or delete this Sale, all its items are automatically saved, updated, or deleted with it. `orphanRemoval = true` means if you delete an item from this list, it is automatically wiped out from the database.
    private List<SaleItem> items = new ArrayList<>(); // Bidirectional dynamic list collection capturing line item records for this invoice
}
