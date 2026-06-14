package com.mishkat.Pharmacy.service;

import com.mishkat.Pharmacy.dto.response.DistrictResponseDTO;
import com.mishkat.Pharmacy.entity.District;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DistrictService {

    District save(District d);
    List<District> getAll();
    Optional<District> getbyId(Integer id);
    void delete(Integer id);

    List<DistrictResponseDTO> getDistrictByDivisionId(Integer divisionId);
    List<DistrictResponseDTO> getDistrictByDivisionName(String divisionName);

}
