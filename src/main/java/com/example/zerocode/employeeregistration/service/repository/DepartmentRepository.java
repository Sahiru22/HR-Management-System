package com.example.zerocode.employeeregistration.service.repository;

import com.example.zerocode.employeeregistration.service.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
