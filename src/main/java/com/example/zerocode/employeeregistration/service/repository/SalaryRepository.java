package com.example.zerocode.employeeregistration.service.repository;

import com.example.zerocode.employeeregistration.service.model.Employee;
import com.example.zerocode.employeeregistration.service.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository extends JpaRepository<Salary, Long> {

  Salary findByEmployee(Employee employee);
}
