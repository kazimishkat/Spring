package com.emranhss.SmartPharmacy.controller;

import com.emranhss.SmartPharmacy.entity.PoliceStation;
import com.emranhss.SmartPharmacy.service.PoliceStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policeStation/")
public class PoliceStationController {

    @Autowired
    private PoliceStationService policeStationService;

    @PostMapping
    public void save(@RequestBody PoliceStation p){
        policeStationService.saveOrUpdate(p);
    }

    @GetMapping
    public List<PoliceStation> getAll(){
        return policeStationService.getAll();
    }

}
