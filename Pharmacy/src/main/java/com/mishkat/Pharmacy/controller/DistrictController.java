package com.mishkat.Pharmacy.controller;

import com.mishkat.Pharmacy.dto.response.DistrictResponseDTO;
import com.mishkat.Pharmacy.entity.District;
import com.mishkat.Pharmacy.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/district/")
public class DistrictController {

    @Autowired
    DistrictService districtService;

    @PostMapping
    public ResponseEntity<District> save(@RequestBody District d){
        District savedDistrict=districtService.save(d);
        return ResponseEntity.ok(savedDistrict);
    }

    @GetMapping
    public ResponseEntity<List<District>> getAll(){
        List<District> list=districtService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<District>> getById(@PathVariable Integer id){
        Optional<District> d=districtService.getbyId(id);
        return ResponseEntity.ok(d);
    }

    @PutMapping("{id}")
    public ResponseEntity<District> update(@PathVariable Integer id,@RequestBody District d){
        District updatedDistrict=districtService.save(d);
        return ResponseEntity.ok(updatedDistrict);
    }

//    get district by division id
    @GetMapping("division/{id}")
    public List<DistrictResponseDTO> getDistrictByDivisionId(@PathVariable Integer divisionId){
        return districtService.getDistrictByDivisionId(divisionId);
    }
    //    get district by division name
    @GetMapping("division/name/{name}")
    public List<DistrictResponseDTO> getDistrictByDivisionName(@PathVariable String divisionName){
        return districtService.getDistrictByDivisionName(divisionName);
    }

}
