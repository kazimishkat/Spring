package com.mishkat.Pharmacy.service;

import com.mishkat.Pharmacy.entity.Country;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CountryService {
    Country save(Country c);
    List<Country> getAll();
    Optional<Country> getByid(Integer id);
    void delete(Integer id);
}
