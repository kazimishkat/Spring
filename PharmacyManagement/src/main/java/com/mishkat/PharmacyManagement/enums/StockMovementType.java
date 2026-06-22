package com.mishkat.PharmacyManagement.enums;

public enum StockMovementType {
    PURCHASE,         // Increase: New stock from supplier purchase order
    SALE,             // Decrease: Customer check-out deduction
    SALE_RETURN,      // Increase: Returned by customer, added back to active shelf
    PURCHASE_RETURN,  // Decrease: Faulty or expired stock sent back to supplier
    TRANSFER_IN,      // Increase: Inventory received from another branch
    TRANSFER_OUT,     // Decrease: Inventory shipped out to another branch
    ADJUSTMENT        // Increase/Decrease: Stock discrepancies fixed after manual audit
}
