package com.example.zerocode.employeeregistration.service.repository;

import com.example.zerocode.employeeregistration.service.model.Employee;
import com.example.zerocode.employeeregistration.service.model.IssuedItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssuedItemRepository extends JpaRepository<IssuedItem, Long> {

  List<IssuedItem> findByEmployee(Employee employee);
}
