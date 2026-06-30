package com.mishkat.PharmacyManagement.serviceImp;

import com.mishkat.PharmacyManagement.dto.mapper.CustomerMapper;
import com.mishkat.PharmacyManagement.dto.requestDTO.CustomerRequestDto;
import com.mishkat.PharmacyManagement.dto.responseDTO.CustomerResponseDto;
import com.mishkat.PharmacyManagement.entity.Customer;
import com.mishkat.PharmacyManagement.repository.CustomerRepository;
import com.mishkat.PharmacyManagement.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public CustomerResponseDto createCustomer(CustomerRequestDto dto) {
        // ডুপ্লিকেট ফোন এবং ইমেইল চেক করা হচ্ছে
        if (customerRepository.findByPhone(dto.getPhone()).isPresent()) {
            throw new RuntimeException("Customer with this phone number already exists: " + dto.getPhone());
        }
        if (dto.getEmail() != null && customerRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Customer with this email already exists: " + dto.getEmail());
        }

        Customer customer = CustomerMapper.toEntity(dto);
        customer.setIsActive(true); // নতুন কাস্টমার বাই ডিফল্ট অ্যাকটিভ

        Customer savedCustomer = customerRepository.save(customer);
        return CustomerMapper.toDTO(savedCustomer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponseDto> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(CustomerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponseDto> getActiveCustomers() {
        return customerRepository.findByIsActiveTrue().stream()
                .map(CustomerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponseDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        return CustomerMapper.toDTO(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponseDto getCustomerByPhone(String phone) {
        Customer customer = customerRepository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("Customer not found with phone: " + phone));
        return CustomerMapper.toDTO(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponseDto getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found with email: " + email));
        return CustomerMapper.toDTO(customer);
    }

    @Override
    @Transactional
    public CustomerResponseDto updateCustomer(Long id, CustomerRequestDto dto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        // আপডেট করার সময় ফোন নম্বর অন্য কারো সাথে মিলে যাচ্ছে কি না চেক করা
        if (dto.getPhone() != null && !dto.getPhone().equals(customer.getPhone())) {
            if (customerRepository.findByPhone(dto.getPhone()).isPresent()) {
                throw new RuntimeException("Phone number already exists: " + dto.getPhone());
            }
        }

        // আপডেট করার সময় ইমেইল অন্য কারো সাথে মিলে যাচ্ছে কি না চেক করা
        if (dto.getEmail() != null && !dto.getEmail().equals(customer.getEmail())) {
            if (customerRepository.findByEmail(dto.getEmail()).isPresent()) {
                throw new RuntimeException("Email already exists: " + dto.getEmail());
            }
        }

        CustomerMapper.updateEntity(customer, dto);
        Customer savedCustomer = customerRepository.save(customer);
        return CustomerMapper.toDTO(savedCustomer);
    }

    @Override
    @Transactional
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        customerRepository.delete(customer);
    }
}
