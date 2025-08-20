package com.example.zerocode.employeeregistration.service.repository;

import com.example.zerocode.employeeregistration.service.model.EducationalQualification;
import com.example.zerocode.employeeregistration.service.model.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationalQualificationRepository extends
    JpaRepository<EducationalQualification, Long> {

  List<EducationalQualification> findByEmployee(Employee employee);
}

