package com.example.zerocode.employeeregistration.service.repository;

import com.example.zerocode.employeeregistration.service.model.Department;
import com.example.zerocode.employeeregistration.service.model.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  List<Employee> findByDepartment(Department department);
}




