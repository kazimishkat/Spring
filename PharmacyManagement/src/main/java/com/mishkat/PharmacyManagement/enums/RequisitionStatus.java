package com.mishkat.PharmacyManagement.enums;

public enum RequisitionStatus {
    DRAFT,                // Being created by a branch, not yet sent to central
    SUBMITTED,            // Sent to central warehouse/management for review
    APPROVED,             // Request accepted, waiting to be sent out
    REJECTED,             // Request denied by central authority
    PARTIALLY_FULFILLED,  // Some requested items are on the way, others are unavailable
    COMPLETED             // All requested items received by the requesting branch
}
