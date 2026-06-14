package com.mishkat.Pharmacy.serviceimpl;

import com.mishkat.Pharmacy.entity.User;
import com.mishkat.Pharmacy.repository.UserRepository;
import com.mishkat.Pharmacy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User save(User u) {
        return userRepository.save(u);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
userRepository.deleteById(id);
    }
}
