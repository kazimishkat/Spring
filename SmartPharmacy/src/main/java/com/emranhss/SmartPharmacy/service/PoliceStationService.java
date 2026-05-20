package com.emranhss.SmartPharmacy.service;

import com.emranhss.SmartPharmacy.entity.PoliceStation;
import com.emranhss.SmartPharmacy.repository.PoliceStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoliceStationService {
    @Autowired
    private PoliceStationRepository stationRepository;

    public List<PoliceStation> getAll(){
        return stationRepository.findAll();
    }

    public void saveOrUpdate(PoliceStation p){
        stationRepository.save(p);
    }

    public Optional<PoliceStation> getById(long id){
        return stationRepository.findById(id);
    }

    public void delete(long id){
        stationRepository.deleteById(id);
    }
}
