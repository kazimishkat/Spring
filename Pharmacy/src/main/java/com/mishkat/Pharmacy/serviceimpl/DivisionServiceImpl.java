package com.mishkat.Pharmacy.serviceimpl;

import com.mishkat.Pharmacy.dto.DivisionDTO;
import com.mishkat.Pharmacy.entity.Country;
import com.mishkat.Pharmacy.entity.Division;
import com.mishkat.Pharmacy.repository.CountryRepository;
import com.mishkat.Pharmacy.repository.DivisionRepository;
import com.mishkat.Pharmacy.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DivisionServiceImpl implements DivisionService {

    @Autowired
    DivisionRepository divisionRepository;

    @Autowired
    CountryRepository countryRepository;

    @Override
    public Division save(Division d) {
        Integer countryId= d.getCountry().getId();
        Country c=countryRepository.findById(countryId).orElseThrow(()-> new RuntimeException("Country Not Found"));
        d.setCountry(c);
        return divisionRepository.save(d);
    }

    @Override
    public List<Division> getAll() {
        return divisionRepository.findAll();
    }

    @Override
    public Optional<Division> getById(Integer id) {
        return divisionRepository.findById(id);
    }

    @Override
    public Void delete(Integer id) {
       divisionRepository.deleteById(id);

        return null;
    }

    @Override
    public List<DivisionDTO> getDivisionByCountryId(Integer coutryId) {

        List<Division> list=divisionRepository.findByCountryId(coutryId);
        return list.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<DivisionDTO> getDivisionByCountryName(String coutryName) {
        List<Division> list=divisionRepository.findByCountryName(coutryName);
        return list.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public DivisionDTO  convertToDto(Division division){
        return new DivisionDTO(
                division.getId(),
                division.getName(),
                division.getCountry().getId(),
                division.getCountry().getName()
        );
    }
}
