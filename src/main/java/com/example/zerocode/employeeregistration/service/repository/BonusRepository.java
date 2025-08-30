package com.example.zerocode.employeeregistration.service.repository;

import com.example.zerocode.employeeregistration.service.model.Bonus;
import com.example.zerocode.employeeregistration.service.model.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BonusRepository extends JpaRepository<Bonus, Long>,
    QuerydslPredicateExecutor<Bonus> {

  List<Bonus> findByEmployee(Employee employee);
}
