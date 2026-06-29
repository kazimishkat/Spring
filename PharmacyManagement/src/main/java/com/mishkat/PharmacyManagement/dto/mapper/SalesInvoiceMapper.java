package com.mishkat.PharmacyManagement.dto.mapper;

import com.mishkat.PharmacyManagement.dto.requestDTO.SalesInvoiceItemRequestDto;
import com.mishkat.PharmacyManagement.dto.requestDTO.SalesInvoiceRequestDto;
import com.mishkat.PharmacyManagement.dto.responseDTO.SalesInvoiceResponseDto;
import com.mishkat.PharmacyManagement.entity.SalesInvoice;
import com.mishkat.PharmacyManagement.entity.SalesInvoiceItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SalesInvoiceMapper {
    // Entity → Response DTO
    public static SalesInvoiceResponseDto toDTO(SalesInvoice invoice) {
        if (invoice == null) return null;

        SalesInvoiceResponseDto dto = new SalesInvoiceResponseDto();
        dto.setId(invoice.getId());
        dto.setInvoiceNumber(invoice.getInvoiceNumber());
        dto.setInvoiceDate(invoice.getInvoiceDate());
        dto.setSubTotal(invoice.getSubTotal());
        dto.setDiscountAmount(invoice.getDiscountAmount());
        dto.setVatAmount(invoice.getVatAmount());
        dto.setTotalAmount(invoice.getTotalAmount());
        dto.setPaidAmount(invoice.getPaidAmount());
        dto.setDueAmount(invoice.getDueAmount());
        dto.setStatus(invoice.getStatus());

        // রিলেশনাল ডেটা হ্যান্ডেলিং
        if (invoice.getBranch() != null) {
            dto.setBranchId(invoice.getBranch().getId());
            dto.setBranchName(invoice.getBranch().getName());
        }
        if (invoice.getCustomer() != null) {
            dto.setCustomerId(invoice.getCustomer().getId());
            dto.setCustomerName(invoice.getCustomer().getName());
        }
        if (invoice.getPrescription() != null) {
            dto.setPrescriptionId(invoice.getPrescription().getId());
        }
        if (invoice.getSoldBy() != null) {
            dto.setSoldByName(invoice.getSoldBy().getFullName());
        }

        // চাইল্ড লিস্ট (Items) কনভার্শন - স্ট্রিম এপিআই ব্যবহার করে প্রতিটি আইটেম ম্যাপ করা হচ্ছে
        if (invoice.getItems() != null) {
            List<SalesInvoiceResponseDto.SalesInvoiceItemResponseDto> itemDTOs = invoice.getItems().stream()
                    .map(SalesInvoiceMapper::toItemDTO)
                    .collect(Collectors.toList());
            dto.setItems(itemDTOs);
        }

        return dto;
    }

    // Helper Method: চাইল্ড Entity থেকে চাইল্ড DTO
    private static SalesInvoiceResponseDto.SalesInvoiceItemResponseDto toItemDTO(SalesInvoiceItem item) {
        SalesInvoiceResponseDto.SalesInvoiceItemResponseDto itemDto = new SalesInvoiceResponseDto.SalesInvoiceItemResponseDto();
        itemDto.setId(item.getId());
        itemDto.setQuantity(item.getQuantity());
        itemDto.setUnitPrice(item.getUnitPrice());
        itemDto.setDiscountType(item.getDiscountType());
        itemDto.setDiscountValue(item.getDiscountValue());

        // Batch Relation
        if (item.getBatch() != null) {
            itemDto.setBatchId(item.getBatch().getId());
            itemDto.setBatchNumber(item.getBatch().getBatchNumber());
            if (item.getBatch().getMedicine() != null) {
                itemDto.setMedicineBrandName(item.getBatch().getMedicine().getBrandName());
            }
        }
        return itemDto;
    }

    // Request DTO → Entity
    public static SalesInvoice toEntity(SalesInvoiceRequestDto dto) {
        if (dto == null) return null;

        SalesInvoice invoice = new SalesInvoice();
        invoice.setInvoiceNumber(dto.getInvoiceNumber());
        invoice.setInvoiceDate(dto.getInvoiceDate());
        invoice.setSubTotal(dto.getSubTotal());
        invoice.setDiscountAmount(dto.getDiscountAmount());
        invoice.setVatAmount(dto.getVatAmount());
        invoice.setTotalAmount(dto.getTotalAmount());
        invoice.setPaidAmount(dto.getPaidAmount());
        invoice.setDueAmount(dto.getDueAmount());
        invoice.setStatus(dto.getStatus());

        // Parent অবজেক্টগুলো (Branch, Customer, User) Service লেয়ারে ID দিয়ে খুঁজে এনে সেট করতে হবে

        // চাইল্ড লিস্ট ইনিশিয়ালাইজেশন
        if (dto.getItems() != null) {
            List<SalesInvoiceItem> items = new ArrayList<>();
            for (SalesInvoiceItemRequestDto itemDto : dto.getItems()) {
                SalesInvoiceItem item = new SalesInvoiceItem();
                item.setQuantity(itemDto.getQuantity());
                item.setUnitPrice(itemDto.getUnitPrice());
                item.setDiscountType(itemDto.getDiscountType());
                item.setDiscountValue(itemDto.getDiscountValue());

                // Parent রেফারেন্স সেট করা হচ্ছে (যাতে ডেটাবেস জানে এই আইটেম কোন ইনভয়েসের)
                item.setInvoice(invoice);
                // Batch Service লেয়ারে সেট করতে হবে
                items.add(item);
            }
            invoice.setItems(items);
        }

        return invoice;
    }
}
