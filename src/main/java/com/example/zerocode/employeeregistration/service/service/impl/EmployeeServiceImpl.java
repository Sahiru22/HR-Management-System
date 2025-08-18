package com.example.zerocode.employeeregistration.service.service.impl;

import com.example.zerocode.employeeregistration.service.controller.request.CreateEmployeeRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateEmployeeResponse;
import com.example.zerocode.employeeregistration.service.controller.response.EmployeeResponse;
import com.example.zerocode.employeeregistration.service.exception.DepartmentNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.model.Department;
import com.example.zerocode.employeeregistration.service.model.Employee;
import com.example.zerocode.employeeregistration.service.repository.DepartmentRepository;
import com.example.zerocode.employeeregistration.service.repository.EmployeeRepository;
import com.example.zerocode.employeeregistration.service.service.EmployeeService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final DepartmentRepository departmentRepository;
  private final ModelMapper modelMapper;

  @Override
  public CreateEmployeeResponse add(CreateEmployeeRequest request)
      throws DepartmentNotFoundException {

    var employee = new Employee();
    modelMapper.map(request, employee);
    if (request.getDepartmentId() != null) {
      Long departmentId = request.getDepartmentId();
      Department department = departmentRepository.findById(departmentId)
          .orElseThrow(() -> new DepartmentNotFoundException(
              "Department not found with id: " + departmentId));
      employee.setDepartment(department);
    }
    employeeRepository.save(employee);

    CreateEmployeeResponse response = new CreateEmployeeResponse();
    response.setId(employee.getId());

    log.info("employee added successfully. employee id:{}", employee.getId());

    return response;

  }

  @Override
  public List<EmployeeResponse> getAll() {
    log.info("Getting all employees");

    List<Employee> employees = employeeRepository.findAll();

    return employees.stream()
        .map(employee -> EmployeeResponse.builder()
            .id(employee.getId())
            .firstName(employee.getFirstName())
            .lastName(employee.getLastName())
            .age(employee.getAge())
            .address(employee.getAddress())
            .gender(employee.getGender())
            .email(employee.getEmail())
            .bloodGroup(employee.getBloodGroup())
            .maritalStatus(employee.getMaritalStatus())
            .phoneNumber(employee.getPhoneNumber())
            .birthDate(employee.getBirthDate())
            .build())
        .toList();
  }

  @Override
  public EmployeeResponse getById(Long employeeId) throws EmployeeNotFoundException {
    log.info("Get employee details by id : {}", employeeId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    return EmployeeResponse.builder()
        .id(employee.getId())
        .firstName(employee.getFirstName())
        .lastName(employee.getLastName())
        .age(employee.getAge())
        .address(employee.getAddress())
        .gender(employee.getGender())
        .email(employee.getEmail())
        .bloodGroup(employee.getBloodGroup())
        .maritalStatus(employee.getMaritalStatus())
        .phoneNumber(employee.getPhoneNumber())
        .birthDate(employee.getBirthDate())
        .build();
  }

  @Override
  public CreateEmployeeResponse deleteById(Long employeeId) throws EmployeeNotFoundException {
    log.info("employee delete by id : {}", employeeId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    employeeRepository.deleteById(employee.getId());

    CreateEmployeeResponse response = new CreateEmployeeResponse();
    response.setId(employee.getId());

    return response;
  }

  @Override
  public CreateEmployeeResponse updateById(Long employeeId, CreateEmployeeRequest request)
      throws EmployeeNotFoundException, DepartmentNotFoundException {
    log.info("employee update by id : {}", employeeId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    modelMapper.map(request, employee);
    if (request.getDepartmentId() != null) {
      Long departmentId = request.getDepartmentId();
      Department department = departmentRepository.findById(departmentId)
          .orElseThrow(() -> new DepartmentNotFoundException(
              "Department not found with id: " + departmentId));
      employee.setDepartment(department);
    }
    employeeRepository.save(employee);

    CreateEmployeeResponse response = new CreateEmployeeResponse();
    response.setId(employee.getId());

    return response;

  }

  @Override
  public List<EmployeeResponse> getAllEmployeeByDepartmentId(Long departmentId)
      throws DepartmentNotFoundException {
    log.info("Getting all employees with department id: {}", departmentId);

    Department department = departmentRepository.findById(departmentId)
        .orElseThrow(
            () -> new DepartmentNotFoundException("Not found department with id: " + departmentId));

    List<Employee> employees = employeeRepository.findByDepartment(department);

    return employees.stream()
        .map(employee -> EmployeeResponse.builder()
            .id(employee.getId())
            .firstName(employee.getFirstName())
            .lastName(employee.getLastName())
            .age(employee.getAge())
            .address(employee.getAddress())
            .gender(employee.getGender())
            .email(employee.getEmail())
            .bloodGroup(employee.getBloodGroup())
            .maritalStatus(employee.getMaritalStatus())
            .phoneNumber(employee.getPhoneNumber())
            .birthDate(employee.getBirthDate())
            .build())
        .toList();
  }
}


