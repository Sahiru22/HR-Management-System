package com.example.zerocode.employeeregistration.service.repository;

import com.example.zerocode.employeeregistration.service.model.Allowance;
import com.example.zerocode.employeeregistration.service.model.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface AllowanceRepository extends JpaRepository<Allowance, Long>,
    QuerydslPredicateExecutor<Allowance> {

  List<Allowance> findByEmployee(Employee employee);
}
