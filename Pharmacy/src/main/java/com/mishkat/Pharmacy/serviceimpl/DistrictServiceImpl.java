package com.mishkat.Pharmacy.serviceimpl;

import com.mishkat.Pharmacy.dto.response.DistrictResponseDTO;
import com.mishkat.Pharmacy.entity.District;
import com.mishkat.Pharmacy.entity.Division;
import com.mishkat.Pharmacy.repository.DistrictRepository;
import com.mishkat.Pharmacy.repository.DivisionRepository;
import com.mishkat.Pharmacy.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    DivisionRepository divisionRepository;
    @Override
    public District save(District d) {
        Integer divisionId=d.getDivision().getId();
        Division dv=divisionRepository.findById(divisionId).orElseThrow(()-> new RuntimeException("DivisionNotFound"));
        d.setDivision(dv);
        return districtRepository.save(d);
    }

    @Override
    public List<District> getAll() {
        return districtRepository.findAll();
    }

    @Override
    public Optional<District> getbyId(Integer id) {
        return districtRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<DistrictResponseDTO> getDistrictByDivisionId(Integer divisionId) {
        List<District> list=districtRepository.findByDivisionId(divisionId);
        return list.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<DistrictResponseDTO> getDistrictByDivisionName(String divisionName) {
        List<District> list=districtRepository.findByDivisionName(divisionName);
        return list.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public DistrictResponseDTO convertToDto(District d){
        return new DistrictResponseDTO(
                d.getId(),
                d.getName(),
                d.getDivision().getId(),
                d.getDivision().getName(),
                d.getDivision().getCountry().getId(),
                d.getDivision().getCountry().getName(),
                d.getDivision().getCountry().getCode()
        );
    }
}
