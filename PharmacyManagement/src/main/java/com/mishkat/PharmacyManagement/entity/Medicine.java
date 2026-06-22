package com.mishkat.PharmacyManagement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity // Identifies standard persistence entity framework class
@Data // Implements component getter/setter boilerplate
@Table(name = "medicines") // Maps class structures to 'medicines' data table
public class Medicine {
    @Id // Database internal row index identification
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Identity column mapping
    private Long id; // Global asset reference key index

    @Column(unique = true, nullable = false) // Requires unique SKU barcoding structures
    private String medicineCode; // Universal barcode SKU identifying the item (e.g., "SKU-NAPA-500")

    @Column(nullable = false) // Standard brand packaging identification name
    private String brandName; // Commercially marketed name of the product item (e.g., "Napa Extra")

    @ManyToOne // Many distinct branded medicines can share the exact same chemical Generic configuration (Many-To-One)
    @JoinColumn(name = "generic_id", nullable = false) // Sets up a mandatory Foreign Key column 'generic_id' that links straight to the 'generics' master table
    private Generic generic; // The chemical composition taxonomy link of this drug

    @ManyToOne // Many distinct brand medicines can be built by the same core manufacturing vendor (Many-To-One)
    @JoinColumn(name = "manufacturer_id", nullable = false) // Creates a mandatory Foreign Key named 'manufacturer_id' mapped to 'manufacturers' table
    private Manufacturer manufacturer; // The commercial pharmaceutical house creator of this medicine

    private String strength; // Mass/volume content metrics of active agent ingredients (e.g., "500 mg", "10 ml")
    private Double purchasePrice; // Benchmark cost price paid to acquire one unit from suppliers
    private Double salePrice; // Standard regular retail shelf pricing rate targeted to consumers
    private Integer reorderLevel; // Minimum safety threshold quantity count before automated alerts flag restocking requirements
    private boolean prescriptionRequired = false; // Restriction checkpoint toggle blocking retail sale unless certified by a physician
}
