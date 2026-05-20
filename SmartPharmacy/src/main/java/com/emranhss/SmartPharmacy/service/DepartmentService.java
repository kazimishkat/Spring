package com.emranhss.SmartPharmacy.service;

import com.emranhss.SmartPharmacy.entity.Department;
import com.emranhss.SmartPharmacy.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDep(){
        return departmentRepository.findAll();
    }

    public Department saveOrUpdate(Department d){
      return   departmentRepository.save(d);
    }

    public Optional<Department> getBYDepId(long id){
        return departmentRepository.findById(id);
    }

    public void deleteDep(long id){
        departmentRepository.deleteById(id);
    }
}
