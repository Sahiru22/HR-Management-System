package com.example.zerocode.employeeregistration.service.repository;

import com.example.zerocode.employeeregistration.service.model.DependentDetail;
import com.example.zerocode.employeeregistration.service.model.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DependentDetailRepository extends JpaRepository<DependentDetail, Long> {

  List<DependentDetail> findByEmployee(Employee employee);
}
