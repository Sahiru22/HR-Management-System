package com.example.zerocode.employeeregistration.service.repository;

import com.example.zerocode.employeeregistration.service.model.Allowance;
import com.example.zerocode.employeeregistration.service.model.Department;
import com.example.zerocode.employeeregistration.service.model.Employee;
import com.example.zerocode.employeeregistration.service.model.Gender;
import com.example.zerocode.employeeregistration.service.model.MaritalStatus;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class AllowanceRepositoryTest {

  @Autowired
  private AllowanceRepository allowanceRepository;

  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private DepartmentRepository departmentRepository;

  private Employee employee;

  @BeforeEach
  public void setup() {
    allowanceRepository.deleteAll();
    employeeRepository.deleteAll();
    departmentRepository.deleteAll();

    Department dept = new Department();
    dept.setName("IT");
    Department savedDept = departmentRepository.save(dept);

    Employee emp = new Employee();
    emp.setFirstName("Joe");
    emp.setLastName("Root");
    emp.setAge(28);
    emp.setAddress("Colombo");
    emp.setGender(Gender.MALE);
    emp.setEmail("Joy@gmail.com");
    emp.setBloodGroup("O+");
    emp.setMaritalStatus(MaritalStatus.SINGLE);
    emp.setBirthDate(LocalDate.of(1995, 1, 1));
    emp.setPhoneNumber("0712345678");
    emp.setDepartment(savedDept);

    employee = employeeRepository.save(emp);
  }

  @Test
  void testSaveAndFindAllowanceByEmployee() {
    Allowance allowance = new Allowance();
    allowance.setAllowanceType("Transport");
    allowance.setAllowanceFee(BigDecimal.valueOf(5000.00));
    allowance.setAllowanceDate(LocalDate.now());
    allowance.setEmployee(employee);

    Allowance savedAllowance = allowanceRepository.save(allowance);

    Assertions.assertNotNull(savedAllowance);
    Assertions.assertNotNull(savedAllowance.getId());

    List<Allowance> allowances = allowanceRepository.findByEmployee(employee);

    Assertions.assertNotNull(allowances);
    Assertions.assertEquals(1, allowances.size());
    Assertions.assertEquals("Transport", allowances.get(0).getAllowanceType());
    Assertions.assertEquals(employee.getId(), allowances.get(0).getEmployee().getId());
  }

  @Test
  void testFindAllowanceByEmployeeEmpty() {
    List<Allowance> allowances = allowanceRepository.findByEmployee(employee);
    Assertions.assertTrue(allowances.isEmpty());
  }
}
