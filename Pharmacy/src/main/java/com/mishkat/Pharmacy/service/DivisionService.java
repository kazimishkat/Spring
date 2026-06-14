package com.mishkat.Pharmacy.service;

import com.mishkat.Pharmacy.dto.DivisionDTO;
import com.mishkat.Pharmacy.entity.Division;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DivisionService {
    Division save(Division d);
    List<Division> getAll();
    Optional<Division> getById(Integer id);
    Void delete(Integer id);

    List<DivisionDTO> getDivisionByCountryId(Integer coutryId);
    List<DivisionDTO> getDivisionByCountryName(String coutryName);
}
