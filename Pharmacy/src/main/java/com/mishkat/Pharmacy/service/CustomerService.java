package com.mishkat.Pharmacy.service;

import com.mishkat.Pharmacy.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CustomerService {
    Customer save(Customer c,MultipartFile file);
    List<Customer> getAll();
    Optional<Customer> getById(Long id);
    void delete(Long id);}
