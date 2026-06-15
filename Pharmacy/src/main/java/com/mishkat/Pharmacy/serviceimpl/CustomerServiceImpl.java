package com.mishkat.Pharmacy.serviceimpl;

import com.mishkat.Pharmacy.entity.Customer;
import com.mishkat.Pharmacy.entity.User;
import com.mishkat.Pharmacy.repository.CustomerRepository;
import com.mishkat.Pharmacy.repository.UserRepository;
import com.mishkat.Pharmacy.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("D:\\Spring\\Assests")
    private String uploadDir;

    @Override
    public Customer save(Customer c,MultipartFile imageFile) {

        if (imageFile != null && !imageFile.isEmpty()) {
            // Save image for both User and JobSeeker
            String filename = saveImageForCustomer(imageFile, c);
            c.setImage(filename);
        }


        User u = new User();
        u.setName(c.getName());
        u.setEmail(c.getEmail());
        u.setPhone(c.getPhone());
        u.setPassword(c.getPassword());
        u.setRole("CUSTOMER");
        User savedUser = userRepository.save(u);
        c.setUser(savedUser);
        return customerRepository.save(c);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }


    public String saveImageForCustomer(MultipartFile file, Customer c) {

        Path uploadPath = Paths.get(uploadDir + "/customer");
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectory(uploadPath);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        String customerName = c.getName();
        String fileName = customerName.trim().replaceAll("\\s+", "_");

        String savedFileName = fileName + "_" + UUID.randomUUID().toString();

        try {
            Path filePath = uploadPath.resolve(savedFileName);
            Files.copy(file.getInputStream(), filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return savedFileName;
    }
}
