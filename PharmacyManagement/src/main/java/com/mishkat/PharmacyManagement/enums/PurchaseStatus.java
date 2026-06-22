package com.mishkat.PharmacyManagement.enums;

public enum PurchaseStatus {
    DRAFT,             // Order being drafted by procurement team
    ORDERED,           // Purchase Order officially sent out to Supplier
    RECEIVED,          // All items arrived safely and loaded into inventory
    PARTIAL_RECEIVED,  // Part of the shipment arrived; waiting on backorders
    CANCELLED          // Procurement order terminated
}
