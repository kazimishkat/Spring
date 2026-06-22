package com.mishkat.PharmacyManagement.enums;

public enum SaleStatus {
    PENDING,       // Order generated but payment not yet confirmed
    PAID,          // Total amount settled successfully
    PARTIAL_PAID,  // Customer paid a portion (useful for credit/due management)
    CANCELLED      // Transaction invalidated, stock returned to shelf
}
