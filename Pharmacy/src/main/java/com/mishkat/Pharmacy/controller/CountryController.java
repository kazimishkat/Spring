package com.mishkat.Pharmacy.controller;

import com.mishkat.Pharmacy.entity.Country;
import com.mishkat.Pharmacy.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/country/")
public class CountryController {

    @Autowired
    CountryService countryService;

    @PutMapping
    public ResponseEntity<Country> save(@RequestBody Country c){
        Country countrySaved=countryService.save(c);
        return ResponseEntity.ok(countrySaved);
    }

    @GetMapping
    public ResponseEntity<List<Country>> getAll(){
       List<Country> list=countryService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("{id}")
    public ResponseEntity<Country> findById(@PathVariable Integer id){
        Country c=countryService.getByid(id).orElseThrow(()-> new RuntimeException("Country Not Found"));
        return ResponseEntity.ok(c);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        countryService.delete(id);
        return ResponseEntity.ok("Data Deleted");
    }

    @PutMapping("{id}")
    public ResponseEntity<Country> update(@PathVariable Integer id,@RequestBody Country c){
        c.setId(id);
        Country countryUpdated=countryService.save(c);
        return ResponseEntity.ok(countryUpdated);
    }
}
