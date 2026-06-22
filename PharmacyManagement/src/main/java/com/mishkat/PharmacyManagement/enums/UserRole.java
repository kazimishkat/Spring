package com.mishkat.PharmacyManagement.enums;

public enum UserRole {
    SUPER_ADMIN,       // Complete system control across all operations and branches
    CENTRAL_MANAGER,   // Handles central inventory, global purchasing, and suppliers
    BRANCH_MANAGER,    // Manages a single branch's inventory, staff, and requisitions
    PHARMACIST,        // Verifies prescriptions, dispenses medicine, and views stock
    CASHIER,           // Creates sales invoices and processes payments
    STORE_KEEPER       // Manages incoming stock, batch allocations, and transfers
}
