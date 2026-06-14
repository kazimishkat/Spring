package com.mishkat.Pharmacy.serviceimpl;

import com.mishkat.Pharmacy.entity.Country;
import com.mishkat.Pharmacy.repository.CountryRepository;
import com.mishkat.Pharmacy.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceimpl implements CountryService {

    @Autowired
    CountryRepository countryRepository;
    @Override
    public Country save(Country c) {
        return countryRepository.save(c);
    }

    @Override
    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> getByid(Integer id) {
        return countryRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
    countryRepository.deleteById(id);
    }
}
