package com.emranhss.SmartPharmacy.repository;

import com.emranhss.SmartPharmacy.entity.PoliceStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliceStationRepository extends JpaRepository<PoliceStation, Long> {
}
