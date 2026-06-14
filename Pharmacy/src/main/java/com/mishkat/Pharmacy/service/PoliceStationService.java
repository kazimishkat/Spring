package com.mishkat.Pharmacy.service;

import com.mishkat.Pharmacy.entity.PolicStation;
import com.mishkat.Pharmacy.repository.PoliceStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoliceStationService {

    @Autowired
    private PoliceStationRepository policeStationRepository;

    public List<PolicStation> getAll(){
        return policeStationRepository.findAll();
    }

    public PolicStation saveOrUpdate(PolicStation p){
        return policeStationRepository.save(p);
    }

    public Optional<PolicStation> getById(long id){
        return policeStationRepository.findById(id);
    }
    public void delete(long id){
        policeStationRepository.deleteById(id);
    }
}
