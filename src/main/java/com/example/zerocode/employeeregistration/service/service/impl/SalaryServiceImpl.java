package com.example.zerocode.employeeregistration.service.service.impl;

import com.example.zerocode.employeeregistration.service.controller.request.CreateSalaryRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateSalaryResponse;
import com.example.zerocode.employeeregistration.service.controller.response.SalaryResponse;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.SalaryNotFoundException;
import com.example.zerocode.employeeregistration.service.model.Salary;
import com.example.zerocode.employeeregistration.service.repository.EmployeeRepository;
import com.example.zerocode.employeeregistration.service.repository.SalaryRepository;
import com.example.zerocode.employeeregistration.service.service.SalaryService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class SalaryServiceImpl implements SalaryService {

  private final SalaryRepository salaryRepository;
  private final EmployeeRepository employeeRepository;
  private final ModelMapper modelMapper;

  @Override
  public CreateSalaryResponse addSalary(CreateSalaryRequest request, Long employeeId)
      throws EmployeeNotFoundException {
    log.info("successfully adding salaries with employee id:{}", employeeId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id: " + employeeId));

    var salary = new Salary();
    modelMapper.map(request, salary);
    salary.setEmployee(employee);
    salaryRepository.save(salary);

    var response = new CreateSalaryResponse();
    response.setId(salary.getId());

    return response;
  }

  @Override
  public SalaryResponse getSalaryById(Long employeeId) throws EmployeeNotFoundException {
    log.info("getting salary with employee id : {}", employeeId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    var salary = salaryRepository.findByEmployee(employee);

    return SalaryResponse.builder()
        .id(salary.getId())
        .basicSalary(salary.getBasicSalary())
        .salarySchedule(salary.getSalarySchedule())
        .salaryDate(salary.getSalaryDate())
        .employeeName(
            salary.getEmployee().getFirstName() + " " + salary.getEmployee().getLastName())
        .build();
  }

  @Override
  public List<SalaryResponse> getAllSalaries() {
    log.info("getting all salaries");

    List<Salary> salaries = salaryRepository.findAll();

    return salaries.stream()
        .map(salary -> SalaryResponse.builder()
            .id(salary.getId())
            .basicSalary(salary.getBasicSalary())
            .salarySchedule(salary.getSalarySchedule())
            .salaryDate(salary.getSalaryDate())
            .employeeName(
                salary.getEmployee().getFirstName() + " " + salary.getEmployee().getLastName())
            .build())
        .toList();
  }

  @Override
  public CreateSalaryResponse updateSalary(CreateSalaryRequest request, Long employeeId,
      Long salaryId) throws EmployeeNotFoundException, SalaryNotFoundException {
    log.info("updating salary with employee id : {}", employeeId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    var salary = salaryRepository.findById(salaryId)
        .orElseThrow(() -> new SalaryNotFoundException("Not found salary with id:" + salaryId));

    modelMapper.map(request, salary);
    salary.setEmployee(employee);
    salaryRepository.save(salary);

    var response = new CreateSalaryResponse();
    response.setId(salary.getId());

    return response;
  }

  @Override
  public CreateSalaryResponse deleteSalary(Long employeeId, Long salaryId)
      throws SalaryNotFoundException {
    log.info("deleting salary with employee id : {}", employeeId);

    employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new SalaryNotFoundException("Not found employee with id: " + employeeId));

    var salary = salaryRepository.findById(salaryId)
        .orElseThrow(() -> new SalaryNotFoundException("Not found salary with id: " + salaryId));

    salaryRepository.delete(salary);

    var response = new CreateSalaryResponse();
    response.setId(salary.getId());

    return response;
  }
}
