package com.mishkat.Pharmacy.controller;

import com.mishkat.Pharmacy.dto.DivisionDTO;
import com.mishkat.Pharmacy.entity.Division;
import com.mishkat.Pharmacy.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/division/")
public class DivisionController {
    @Autowired
    DivisionService divisionService;

    @PostMapping
    public ResponseEntity<Division> save(@RequestBody Division d){
        Division savedDivision=divisionService.save(d);
        return ResponseEntity.ok(savedDivision);
    }

    @GetMapping
    public ResponseEntity<List<Division>> getAll(){
        List<Division> list=divisionService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Division>> getById(@PathVariable Integer id){
        Optional<Division> d=divisionService.getById(id);
        return ResponseEntity.ok(d);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        divisionService.delete(id);
        return ResponseEntity.ok("Division Delete");
    }

    @PutMapping("{id}")
    public ResponseEntity<Division> update(@PathVariable Integer id,@RequestBody Division d){
        Division updateDivision=divisionService.save(d);
        return ResponseEntity.ok(updateDivision);
    }

//    find division by countryId
    @GetMapping("country/{id}")
    public List<DivisionDTO> getDivisionByCountryId(@PathVariable Integer id){
        return divisionService.getDivisionByCountryId(id);
    }

    //    find division by countryname
    @GetMapping("country/name/{name}")
    public List<DivisionDTO> getDivisionByCountryName(@PathVariable String name){
        return divisionService.getDivisionByCountryName(name);
    }
}
