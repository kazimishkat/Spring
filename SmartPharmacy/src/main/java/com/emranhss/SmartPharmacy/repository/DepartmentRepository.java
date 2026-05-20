package com.emranhss.SmartPharmacy.repository;

import com.emranhss.SmartPharmacy.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
