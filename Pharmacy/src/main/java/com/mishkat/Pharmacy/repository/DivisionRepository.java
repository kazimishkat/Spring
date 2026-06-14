package com.mishkat.Pharmacy.repository;

import com.mishkat.Pharmacy.entity.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DivisionRepository extends JpaRepository<Division,Integer> {

    List<Division> findByCountryId(Integer countryId);
    List<Division> findByCountryName(String countryName);
}
