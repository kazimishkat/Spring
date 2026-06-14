package com.mishkat.Pharmacy.controller;

import com.mishkat.Pharmacy.entity.PolicStation;
import com.mishkat.Pharmacy.service.PoliceStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policeStation/")
public class PoliceStationController {

    @Autowired
    private PoliceStationService policeStationService;

    @PostMapping
    public ResponseEntity<PolicStation> save(@RequestBody PolicStation p){
      PolicStation savedPoliceStation=  policeStationService.saveOrUpdate(p);
        return new ResponseEntity<>(savedPoliceStation, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PolicStation>> getAll(){
        List<PolicStation> policeStattionList= policeStationService.getAll();
        return ResponseEntity.ok(policeStattionList);
    }

    @GetMapping("{id}")
    public ResponseEntity<PolicStation> getByid(@PathVariable Long id){
        PolicStation policStation=policeStationService.getById(id).orElseThrow(()-> new RuntimeException("police Station not found"));
        return ResponseEntity.ok(policStation);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        policeStationService.delete(id);
        return ResponseEntity.ok("police Stattion Deleted");
    }

    @PutMapping("{id}")
    public ResponseEntity<PolicStation> update(@PathVariable Long id,@RequestBody PolicStation p){
        p.setId(id);
        PolicStation policeStationUpdate=policeStationService.saveOrUpdate(p);
        return ResponseEntity.ok(policeStationUpdate);
    }


}
