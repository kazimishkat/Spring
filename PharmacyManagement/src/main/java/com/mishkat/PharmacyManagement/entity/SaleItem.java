package com.mishkat.PharmacyManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Declares child detail row model entity mapping layer
@Data // Lombok automated encapsulation processing injector
@Table(name = "sale_items") // Binds object mapping context strictly inside 'sale_items' database rows
@AllArgsConstructor
@NoArgsConstructor
public class SaleItem {
    @Id // Child entity unique row sequence indicator
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Key sequencing execution configuration
    private Long id; // Surrogate line item sequence identification marker

    @ManyToOne // Many distinct line item rows hook straight back into one parent invoice tracking header (Many-To-One)
    @JoinColumn(name = "sale_id", nullable = false) // Configures mandatory foreign key column mapping connecting down to 'sales' table id
    private Sale sale; // Bidirectional anchor context pointing back up directly to parent sale record

    @ManyToOne // Many sales lines across different invoices can buy the exact same catalog item (Many-To-One)
    @JoinColumn(name = "medicine_id", nullable = false) // Maps essential foreign key tracing down to 'medicines' data table rows
    private Medicine medicine; // Product identity definition being sold on this specific line item receipt line

    private String batchNo; // Exact physical batch container number targeted to fulfill this line deduction (critical for medical safety tracing)
    private Integer quantity; // Count volume metric tracking total items handed over to consumer on this row line
    private Double unitPrice; // Dynamic actual selling rate processed per item unit recorded at runtime checkout
    private Double subTotal; // Product summation multiplication total mapping item calculation (`quantity * unitPrice`)
}
