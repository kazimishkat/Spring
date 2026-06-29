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
    // =========================================================================
    // 1. Entity থেকে Response DTO তে রূপান্তর (Client-কে ডাটা দেখানোর জন্য)
    // =========================================================================

    public SalesInvoiceResponseDto toDTO(SalesInvoice entity) {
        if (entity == null) return null;

        SalesInvoiceResponseDto dto = new SalesInvoiceResponseDto();
        dto.setId(entity.getId());
        dto.setInvoiceNumber(entity.getInvoiceNumber());
        dto.setInvoiceDate(entity.getInvoiceDate());
        dto.setSubTotal(entity.getSubTotal());
        dto.setDiscountAmount(entity.getDiscountAmount());
        dto.setVatAmount(entity.getVatAmount());
        dto.setTotalAmount(entity.getTotalAmount());
        dto.setPaidAmount(entity.getPaidAmount());
        dto.setDueAmount(entity.getDueAmount());
        dto.setStatus(entity.getStatus());

        // [গুরুত্বপূর্ণ]: Lazy Loading এর কারণে NullPointerException এড়াতে নাল-চেক করা হয়েছে
        if (entity.getBranch() != null) {
            dto.setBranchId(entity.getBranch().getId());
            dto.setBranchName(entity.getBranch().getName());
        }

        if (entity.getCustomer() != null) {
            dto.setCustomerId(entity.getCustomer().getId());
            dto.setCustomerName(entity.getCustomer().getName()); // Customer এন্টিটিতে getName() আছে ধরে নেওয়া হয়েছে
        }

        if (entity.getPrescription() != null) {
            dto.setPrescriptionId(entity.getPrescription().getId());
        }

        if (entity.getSoldBy() != null) {
            dto.setSoldByName(entity.getSoldBy().getFullName()); // User এন্টিটি অনুযায়ী মেথড পরিবর্তন করতে পারেন
        }

        // [লিস্ট ম্যাপিং]: ইনভয়েসের আইটেমগুলো ম্যাপ করা হচ্ছে
        if (entity.getItems() != null) {
            dto.setItems(entity.getItems().stream()
                    .map(this::toItemResponseDto)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    /**
     * একটি একক SalesInvoiceItem এন্টিটিকে Response Item DTO-তে রূপান্তর করার মেথড।
     */
    private SalesInvoiceResponseDto.SalesInvoiceItemResponseDto toItemResponseDto(SalesInvoiceItem item) {
        if (item == null) return null;

        SalesInvoiceResponseDto.SalesInvoiceItemResponseDto itemDto = new SalesInvoiceResponseDto.SalesInvoiceItemResponseDto();
        itemDto.setId(item.getId());
        itemDto.setQuantity(item.getQuantity());
        itemDto.setUnitPrice(item.getUnitPrice());
        itemDto.setDiscountType(item.getDiscountType());
        itemDto.setDiscountValue(item.getDiscountValue());

        // এন্টিটির lineTotal কে DTO এর totalAmount এ ম্যাপ করা হচ্ছে
        itemDto.setTotalAmount(item.getLineTotal());
        // [নোট]: taxAmount এন্টিটিতে নেই, তাই এটি Service Layer এ হিসাব করে সেট করতে হতে পারে

        if (item.getBatch() != null) {
            itemDto.setBatchId(item.getBatch().getId());
            itemDto.setBatchNumber(item.getBatch().getBatchNumber());

            // Medicine রিলেশন থেকে ব্র্যান্ডের নাম নেওয়া হচ্ছে
            if (item.getBatch().getMedicine() != null) {
                itemDto.setMedicineBrandName(item.getBatch().getMedicine().getBrandName());
            }
        }

        return itemDto;
    }


    // =========================================================================
    // 2. Request DTO থেকে Entity তে রূপান্তর (ডাটাবেজে ডাটা সেভ বা আপডেট করার জন্য)
    // =========================================================================

    public SalesInvoice toEntity(SalesInvoiceRequestDto dto) {
        if (dto == null) return null;

        SalesInvoice entity = new SalesInvoice();
        entity.setInvoiceNumber(dto.getInvoiceNumber());
        entity.setInvoiceDate(dto.getInvoiceDate());
        entity.setSubTotal(dto.getSubTotal());
        entity.setDiscountAmount(dto.getDiscountAmount());
        entity.setVatAmount(dto.getVatAmount());
        entity.setTotalAmount(dto.getTotalAmount());
        entity.setPaidAmount(dto.getPaidAmount());
        entity.setDueAmount(dto.getDueAmount());
        entity.setStatus(dto.getStatus());

        // [খুবই গুরুত্বপূর্ণ]: JPA CascadeType.ALL কাজ করার জন্য প্যারেন্ট ও চাইল্ডের দ্বিমুখী সম্পর্ক (Bi-directional Link) তৈরি করা
        if (dto.getItems() != null) {
            List<SalesInvoiceItem> items = dto.getItems().stream()
                    .map(itemDto -> {
                        SalesInvoiceItem itemEntity = toItemEntity(itemDto);

                        // চাইল্ডকে বলা হচ্ছে তার প্যারেন্ট কে (Invoice)
                        itemEntity.setInvoice(entity);
                        return itemEntity;
                    })
                    .collect(Collectors.toCollection(ArrayList::new));
            entity.setItems(items);
        }

        // [বিশেষ দ্রষ্টব্য]: branchId, customerId, prescriptionId এবং soldById -এর মত
        // রিলেশনাল অবজেক্টগুলো এখানে সরাসরি ম্যাপ করা সম্ভব নয়। এগুলো Service Layer-এ
        // Repository.findById() থেকে তুলে এনে এই entity-তে সেট করতে হবে।

        return entity;
    }

    /**
     * একটি একক SalesInvoiceItemRequestDto-কে SalesInvoiceItem এন্টিটিতে রূপান্তর করার মেথড।
     */
    private SalesInvoiceItem toItemEntity(SalesInvoiceItemRequestDto itemDto) {
        if (itemDto == null) return null;

        SalesInvoiceItem itemEntity = new SalesInvoiceItem();
        itemEntity.setQuantity(itemDto.getQuantity());
        itemEntity.setUnitPrice(itemDto.getUnitPrice());
        itemEntity.setDiscountType(itemDto.getDiscountType());
        itemEntity.setDiscountValue(itemDto.getDiscountValue());

        // [বিশেষ দ্রষ্টব্য]: lineTotal ফিল্ডটি DTO তে নেই। এটি Service Layer এ ক্যালকুলেট করে সেট করা উচিত।
        // [বিশেষ দ্রষ্টব্য]: batchId ব্যবহার করে আসল MedicineBatch অবজেক্টটি Service Layer-এ সেট করতে হবে।

        return itemEntity;
    }
}
