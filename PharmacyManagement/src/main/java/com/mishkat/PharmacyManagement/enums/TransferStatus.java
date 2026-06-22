package com.mishkat.PharmacyManagement.enums;

public enum TransferStatus {
    PENDING,     // Transfer requested/created, items not yet moved
    IN_TRANSIT,  // Items have left the source branch but not arrived at target
    RECEIVED,    // Target branch has accepted and verified the stock
    CANCELLED    // Transfer aborted before transit took place
}
