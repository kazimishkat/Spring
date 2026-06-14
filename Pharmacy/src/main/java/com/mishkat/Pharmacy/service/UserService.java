package com.mishkat.Pharmacy.service;

import com.mishkat.Pharmacy.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    User save(User u);
    List<User> getAll();
    Optional<User> getById(Long id);
    void delete(Long id);

}
